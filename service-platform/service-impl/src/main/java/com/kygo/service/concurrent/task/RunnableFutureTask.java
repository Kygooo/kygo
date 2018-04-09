package com.kygo.service.concurrent.task;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;

import java.util.concurrent.*;

public abstract class RunnableFutureTask<V> implements RunnableFuture<V> {
	
	private FutureTask<V> task;
	
	public RunnableFutureTask() {
		setupTask();
	}
	
	public abstract V main() throws Exception;
	
	public boolean execute(Executor executor) {
		executor.execute(task);
		return true;
	}
	
	public boolean schedule(TaskScheduler scheduler, Trigger trigger) {
		scheduler.schedule(task, trigger);
		return true;
	}
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return task.cancel(mayInterruptIfRunning);
	}
	
	@Override
	public boolean isCancelled() {
		return task.isCancelled();
	}
	
	@Override
	public boolean isDone() {
		return task.isDone();
	}
	
	@Override
	public V get() throws InterruptedException, ExecutionException {
		return task.get();
	}
	
	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return task.get(timeout, unit);
	}
	
	@Override
	public void run() {
		if (task.isDone()) {
			setupTask();
		}
		task.run();
	}
	
	private void setupTask() {
		task = new FutureTask<V>(new Callable<V>() {
			@Override
			public V call() throws Exception {
				return main();
			}
		});
	}
}
