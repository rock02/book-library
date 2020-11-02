package br.com.alelo.book;

import org.junit.runner.RunWith;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;

@RunWith(Karate.class)
@KarateOptions(
        features = { "classpath:book" }
//        ,tags = {"@tests_book"}
        )
public class Runner {

}
