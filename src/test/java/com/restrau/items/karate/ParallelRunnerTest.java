package com.restrau.items.karate;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.Test;
import com.intuit.karate.Runner.Builder;

public class ParallelRunnerTest {

	@Test
	public void runKarateTests() {
		
		Builder<Karate> karateBuilder=new Builder<>();
		karateBuilder.path("classpath:com/restrau/items/karate");
		karateBuilder.parallel(5);
	}
}
