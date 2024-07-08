package apps.sai.com.movieapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.map
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.Movie.Companion.format
import apps.sai.com.movieapp.data.MovieAdapter
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

abstract class BaseFragment<V : BaseViewModel> : Fragment() {
    private var job: Job? = null
    val viewModel: V by lazy { ViewModelProvider(this)[viewModelClass] }
    protected abstract val viewModelClass: Class<V>
    val dataUrls = listOf("url1", "url2", "url3")
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    fun main2() {
        scope.launch {
            val mainJob =launch {
                println("Starting the main job!")
                // this is an independent coroutine,
                // not a child coroutine
                launch {
                    while (isActive) {
                        delay(100)
                        println("I'm alive!!!")
                    }
                }
            }
            mainJob.invokeOnCompletion {
                println("The main job is completed/cancelled!")
            }

            delay(100)

            mainJob.cancel()

            delay(500)
            println("Finishing main()...")
        }
    }
    fun Job.printOnComplete(message: String) {
        invokeOnCompletion {
            println(message)
        }
    }
    private fun mainException(): Unit = runBlocking {
        // Main Job
        scope.launch {
            // Child 1
            launch {
                while (isActive) {
                    // run
                }
            }.printOnComplete("Child 1 is cancelled!")

            // Child 2
            launch {
                delay(3500)
                println("Here goes boom...")
                throw IllegalArgumentException("Boom!")
            }.printOnComplete("Child 2 is cancelled!")
        }.printOnComplete("Main Job has completed!")

        // Random coroutine on the same scope
        scope.launch {
            while (isActive) {
                // run
            }
        }.printOnComplete("Random coroutine is cancelled!")

        delay(10000)
    }
    fun main1() = runBlocking {

        val childJob = Job()
        val mainJob = scope.launch {
            println("Starting the main job!")

            launch() {
                while (isActive) {
                    delay(10)
                    println("I am child 1!")
                }
            }

            launch {
                while (isActive) {
                    delay(20)
                    println("I am child 2!")
                }
            }
        }
        mainJob.invokeOnCompletion {handler ->

            println("The main job is completed/cancelled! ${ handler?.cause}")
        }

        delay(50)

        // this will cancel the main coroutine
        // and all its children
        scope.cancel()

        delay(500)
        println("Finishing main()...")
    }
    fun main() {
        lifecycleScope.launch {
            val time = measureTimeMillis {
                downloadAllData(dataUrls)
                    .forEach(::println)
            }
            println("Done in: $time ms")
        }
    }

    suspend fun downloadAllData(urls: List<String>): List<Data> {
        return coroutineScope {
            urls.map { url -> async { downloadData(url) } }
                .map { deferred -> deferred.await() }
        }
    }
   suspend fun x(){

    }

    suspend fun downloadData(url: String): Data {
        return withContext(Dispatchers.IO) {
            delay(100)
            Data("I am data from: $url")
        }
    }

    data class Data(val content: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        runBlocking {
            mainException()
            println("I am running on ${Thread.currentThread().name}")
            launch {
                workOnIO() // this might be blocking
            }
        }
    }

    suspend fun workOnIO() {
        withContext(Dispatchers.IO) {
            delay(100)
            println("Now I have switched to ${Thread.currentThread().name}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.menu_search).isVisible = true
        menu.findItem(R.id.menu_favourite).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun loadMovies(flow: Flow<PagingData<Movie>>, adapter: MovieAdapter) {

        job?.cancel()
        job = lifecycleScope.launchWhenStarted {
            viewModel.genres().zip(flow) { genreResponse, pagingData ->
                pagingData.map { movie ->
                    genreResponse.genres.filter { genre ->
                        movie.genreIds.contains(genre.id)
                    }.map {
                        if (movie.genres.isNullOrEmpty()) {
                            movie.genres = arrayListOf()
                        }
                        movie.genres.add(it)
                    }
                    movie.format(requireContext())
                    movie
                }
            }.collect { value ->

                adapter.submitData(value)
            }
        }
    }

    fun initMovieAdapter(): MovieAdapter {
        val adapter = MovieAdapter {
            it.id.let {
                findNavController().navigate(
                    MobileNavigationDirections.actionBaseFragmentToDetails(
                        it
                    )
                )
            }
        }
        return adapter
    }

}