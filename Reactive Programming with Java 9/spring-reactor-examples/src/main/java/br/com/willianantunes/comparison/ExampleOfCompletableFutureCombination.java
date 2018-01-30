package br.com.willianantunes.comparison;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExampleOfCompletableFutureCombination {
	
	public static class SemReactor {		
		public List<String> completableFutureCombination() {
			CompletableFuture<List<String>> ids = ifhIds();

			CompletableFuture<List<String>> result = ids.thenComposeAsync(l -> {
				Stream<CompletableFuture<String>> zip = l.stream().map(i -> {
					CompletableFuture<String> nameTask = ifhName(i);
					CompletableFuture<Integer> statTask = ifhStat(i);

					return nameTask.thenCombineAsync(statTask, (name, stat) -> "Name " + name + " has stats " + stat);
				});
				
				List<CompletableFuture<String>> combinationList = zip.collect(Collectors.toList());
				CompletableFuture<String>[] combinationArray = combinationList.toArray(new CompletableFuture[combinationList.size()]);

				CompletableFuture<Void> allDone = CompletableFuture.allOf(combinationArray);
				
				return allDone.thenApply(v -> combinationList.stream()
						.map(CompletableFuture::join)
						.collect(Collectors.toList()));
			});

			return result.join();
		}

		private CompletableFuture<Integer> ifhStat(String i) {
			return CompletableFuture.supplyAsync(() -> {
				switch (i) {
				case "1a":
					return 103;
				case "2b":
					return 104;
				case "3c":
					return 105;
				case "4d":
					return 106;
				case "5e":
					return 121;

				}
				throw new RuntimeException("Não deveria ter chego aqui!");
			});
		}

		private CompletableFuture<String> ifhName(String i) {
			return CompletableFuture.supplyAsync(() -> {
				switch (i) {
				case "1a":
					return "NameJoe";
				case "2b":
					return "NameBart";
				case "3c":
					return "NameHenry";
				case "4d":
					return "NameNicole";
				case "5e":
					return "NameABSLAJNFOAJNFOANFANSF";

				}
				throw new RuntimeException("Não deveria ter chego aqui!");
			});
		}

		private CompletableFuture<List<String>> ifhIds() {
			return CompletableFuture.supplyAsync(() -> Arrays.asList("1a", "2b", "3c", "4d", "5e"));
		}
	}

	public static class ComReactor {
		public List<String> completableFUtureCombination() {
			Flux<String> ids = ifhrIds(); 

			Flux<String> combinations =
			                ids.flatMap(id -> { 
			                        Mono<String> nameTask = ifhrName(id); 
			                        Mono<Integer> statTask = ifhrStat(id); 

			                        return nameTask.zipWith(statTask, 
			                                        (name, stat) -> "Name " + name + " has stats " + stat);
			                });

			Mono<List<String>> result = combinations.collectList(); 

			return result.block(); 	
		}
		
		private Mono<Integer> ifhrStat(String i) {
			switch (i) {
			case "1a":
				return Mono.just(103);
			case "2b":
				return Mono.just(104);
			case "3c":
				return Mono.just(105);
			case "4d":
				return Mono.just(106);
			case "5e":
				return Mono.just(121);

			}
			throw new RuntimeException("Não deveria ter chego aqui!");
		}

		private Mono<String> ifhrName(String i) {
			switch (i) {
			case "1a":
				return Mono.just("NameJoe");
			case "2b":
				return  Mono.just("NameBart");
			case "3c":
				return  Mono.just("NameHenry");
			case "4d":
				return  Mono.just("NameNicole");
			case "5e":
				return  Mono.just("NameABSLAJNFOAJNFOANFANSF");

			}
			throw new RuntimeException("Não deveria ter chego aqui!");
		}

		private Flux<String> ifhrIds() {
			return Flux.just("1a", "2b", "3c", "4d", "5e");
		}		
	}
}