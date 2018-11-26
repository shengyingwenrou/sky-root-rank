package cn.com.sourcetest.threadpool;

import java.util.concurrent.*;

/**
 * Created by sky.song on 2018/10/11.
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {


    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new ComparableFutureTask(callable);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new  ComparableFutureTask(runnable, value);

        //return new  ComparableFutureTask(runnable, value);
    }

    protected class ComparableFutureTask<V>
            extends FutureTask<V> implements Comparable<ComparableFutureTask<V>> {
        private Object object;


        public ComparableFutureTask(Callable<V> callable) {
            super(callable);
            object = callable;
        }

        public ComparableFutureTask(Runnable runnable, V result) {
            super(runnable, result);
            object = runnable;
        }

        @Override
        @SuppressWarnings("unchecked")
        public int compareTo(ComparableFutureTask<V> o) {
            if (this == o) {
                return 0;
            }
            if (o == null) {
                return -1; // high priority
            }
            if (object != null && o.object != null) {
                if (object.getClass().equals(o.object.getClass())) {
                    if (object instanceof Comparable) {
                        return ((Comparable) object).compareTo(o.object);
                    }
                }
            }
            return 0;
        }
    }
}
