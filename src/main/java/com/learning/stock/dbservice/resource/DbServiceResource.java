package com.learning.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.stock.dbservice.model.Quote;
import com.learning.stock.dbservice.model.Quotes;
import com.learning.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	@Autowired
	private QuotesRepository quotesRepository;	

	public DbServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}

	@GetMapping("/{username}")
	public List<String> getQuoteBytUserName(@PathVariable("username") String username) {
		
		return quotesRepository.findByUserName(username).stream()
				.map(Quote::getQuote)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/add")
	public List<String> saveQuotes(@RequestBody Quotes quotes) {
		
		quotes.getQuotes().stream().forEach(quote -> {
			quotesRepository.save(new Quote(quotes.getUsername(), quote));
		});
		
		return getQuoteBytUserName(quotes.getUsername());
	}
	
	@PostMapping("/delete/{username}")
	public List<String> deleteQuotes(@PathVariable("username") String userName) {
		List<Quote> quotes = quotesRepository.findByUserName(userName);
		quotes.stream().forEach(quote -> {
			quotesRepository.delete(quote);			
		});
		
		return getQuoteBytUserName(userName);
	}
}
