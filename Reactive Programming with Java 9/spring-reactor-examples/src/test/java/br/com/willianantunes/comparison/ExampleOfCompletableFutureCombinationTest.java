package br.com.willianantunes.comparison;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.willianantunes.comparison.ExampleOfCompletableFutureCombination.ComReactor;
import br.com.willianantunes.comparison.ExampleOfCompletableFutureCombination.SemReactor;

public class ExampleOfCompletableFutureCombinationTest {
	@Test
	public void listaContemValoresEsperadosComReactor() {
		ComReactor comReactor = new ComReactor();

		List<String> results = comReactor.completableFUtureCombination();

		Assertions.assertThat(results).containsExactly("Name NameJoe has stats 103", "Name NameBart has stats 104",
				"Name NameHenry has stats 105", "Name NameNicole has stats 106",
				"Name NameABSLAJNFOAJNFOANFANSF has stats 121");
	}
	
	@Test
	public void listaContemValoresEsperadosSemReactor() {
		SemReactor semReactor = new SemReactor();

		List<String> results = semReactor.completableFutureCombination();

		Assertions.assertThat(results).containsExactly("Name NameJoe has stats 103", "Name NameBart has stats 104",
				"Name NameHenry has stats 105", "Name NameNicole has stats 106",
				"Name NameABSLAJNFOAJNFOANFANSF has stats 121");
	}	
}
