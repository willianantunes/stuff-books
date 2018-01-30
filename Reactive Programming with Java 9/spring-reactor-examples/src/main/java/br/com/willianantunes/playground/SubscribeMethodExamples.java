package br.com.willianantunes.playground;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class SubscribeMethodExamples {
	public static void main(String[] args) {
		// sample1();
		// sample2();
		// sample3();
		sample4();		
	}

	public static void sample1() {
		Flux<Integer> ints = Flux.range(1, 3);
		ints.subscribe();
	}

	public static void sample2() {
		Flux<Integer> ints = Flux.range(1, 3);
		ints.subscribe(System.out::print);
	}

	public static void sample3() {
		Flux<Integer> ints = Flux.range(1, 4).map(i -> {
			// Se lançar antes de 2, o trabalho para e não vai até 4
			// Do manual: To make the completion consumer work, we must take care not to trigger an error
			if (i <= 3)
				return i;
			throw new RuntimeException("Got to 4");
		});
		ints.subscribe(i -> System.out.println(i), error -> System.err.println("Error: " + error));
	}

	public static void sample4() {
		SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
		Flux<Integer> ints = Flux.range(1, 4);
		ints.subscribe(i -> System.out.println(i), 
				error -> System.err.println("Error " + error), 
				() -> System.out.println("Done"), 
				s -> ss.request(10));
		ints.subscribe(ss);
	}
	
	/**
	 * The SampleSubscriber class extends BaseSubscriber, which is the recommended abstract 
	 * class for user-defined Subscribers in Reactor. The class offers hooks that can 
	 * be overridden to tune the subscriber’s behavior. By default, it will trigger an 
	 * unbounded request and behave exactly like subscribe(). However, extending BaseSubscriber 
	 * is much more useful when you want a custom request amount.<br /><br />
	 * <strong>LOOK:</strong> You almost certainly want to implement the hookOnError, hookOnCancel, 
	 * and hookOnComplete methods. You may also want to implement the hookFinally method. 
	 * SampleSubscribe is the absolute minimum implementation of a Subscriber that 
	 * performs bounded requests.
	 */
	public static class SampleSubscriber<T> extends BaseSubscriber<T> {
        public void hookOnSubscribe(Subscription subscription) {
                System.out.println("Subscribed");
                request(1);
        }

        public void hookOnNext(T value) {
                System.out.println("Pelo SampleSubscriber: " + value);
                request(1);
        }
	}	
}