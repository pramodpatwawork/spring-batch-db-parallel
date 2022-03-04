package com.mywork.pp.spring.batch.db.parallel;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;





@Configuration
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	 private Map<Long, String> status;
		
	
	@Bean
	public JdbcCursorItemReader<Customer> cursorItemReader(){
		
		JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
		reader.setVerifyCursorPosition(false);
		reader.setSql("SELECT id, firstName, lastName, birthdate FROM customer ORDER BY lastName, firstName");
		reader.setDataSource(dataSource);
		reader.setFetchSize(100);
		reader.setRowMapper(new CustomerRowMapper());
		
		return reader;
	}
	
	
	// This is Thread-safe
	@Bean
	public JdbcPagingItemReader<Customer> pagingItemReader(){
		JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(10);
		reader.setRowMapper(new CustomerRowMapper());
		
		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("id", Order.ASCENDING);
		
		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("select id, firstName, lastName, birthdate");
		queryProvider.setFromClause("from customer");
		queryProvider.setSortKeys(sortKeys);


		reader.setQueryProvider(queryProvider);
		
		return reader;
	}
	
	@Bean
	public ItemWriter<Customer> customerItemWriter(){
		return items -> {
			for(Customer c : items) {
				System.out.println(c.toString());
			}
		};
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    return asyncTaskExecutor;
	}
	
	@Bean
	public Step step1(Map<Long, String> status) {		 
		Step step =  stepBuilderFactory.get("step1")
				.<Customer, Customer>chunk(5)
				//.reader(cursorItemReader())
				//.reader(new Reader(dataSource))
				.reader(new ListReader(new CustomerServiceImpl().getCustomers(), status))
				//.reader(pagingItemReader())
				//.writer(customerItemWriter())
				.writer(new ListWriter(status))
				.taskExecutor(taskExecutor())
				.build();
		
		return step;
				
		
	}
	
	@Bean
	public Job job() {
		status = new HashMap<Long, String>();
		return jobBuilderFactory.get("job")
				.start(step1(status))
				.listener(new JobCompletionListener(status))
				.build();
	}
		
}
