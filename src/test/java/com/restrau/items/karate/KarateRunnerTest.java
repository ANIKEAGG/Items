package com.restrau.items.karate;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;


public class KarateRunnerTest {

	@Test
    public Karate testGetItems(){
        return Karate.run("GetRunner").relativeTo(getClass());
    }
    @Test
    public Karate testPostItems(){
        return Karate.run("PostRunner").relativeTo(getClass());
    }

    @Test
    public Karate testPutItems(){
        return Karate.run("PutRunner").relativeTo(getClass());
    }
}
