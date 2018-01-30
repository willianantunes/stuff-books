package br.com.willianantunes.playground;

import java.util.concurrent.atomic.AtomicLong;

import reactor.core.publisher.Flux;

public class ProgrammaticallyCreatingSequence {
	public static void main(String[] args) {
		// sample1();
		// sample2();
		sample3();
	}

	private static void sample3() {
		Flux<String> flux = Flux.generate(AtomicLong::new, (state, sink) -> {
			long i = state.getAndIncrement();
			sink.next("3 x " + i + " = " + 3 * i);
			if (i == 10)
				sink.complete();
			return state;
		}, (state) -> System.out.println("state: " + state));

		flux.subscribe(System.out::println);
	}

	private static void sample2() {
		Flux<String> flux = Flux.generate(AtomicLong::new, (state, sink) -> {
			long i = state.getAndIncrement();
			sink.next("3 x " + i + " = " + 3 * i);
			if (i == 10)
				sink.complete();
			return state;
		});
		
		flux.subscribe(System.out::println);
	}

	private static void sample1() {
		Flux<String> flux = Flux.generate(() -> 0, (state, sink) -> {
			sink.next("3 x " + state + " = " + 3 * state);
			if (state == 10)
				sink.complete();
			return state + 1;
		});

		flux.subscribe(System.out::println);
	}
}