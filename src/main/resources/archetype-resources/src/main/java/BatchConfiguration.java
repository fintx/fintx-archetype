
package com.beebank.payment;

//import com.beebank.finance.batch.LedgerEntry2VoucherItemProcessor;
//import com.beebank.finance.batch.LedgerEntryItemReader;
//import com.beebank.finance.batch.VoucherItemWriter;
//import com.beebank.finance.voucher.entity.Voucher;
//import com.beebank.ledger.entity.LedgerEntry;
import com.beebank.util.SpringContextUtil;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {
	
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                return null;
            }
        }).build();
        
    }

    @Bean
    public Job job1(Step step1) throws Exception {
        return jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer()).start(step1).build();
    }
    
//    
//    @Bean
//    @JobScope
//    public Step ledgerEntry2VoucherStep(StepBuilderFactory stepBuilderFactory,ItemReader<LedgerEntry> reader,ItemProcessor<LedgerEntry, Voucher> processor,ItemWriter<Voucher> writer) {
//        return stepBuilderFactory.get("ledgerEntry2VoucherStep"+System.currentTimeMillis()).<LedgerEntry,Voucher>chunk(1).reader(reader).processor(processor).writer(writer).build();
//    }
//
//    @Bean
//    public SimpleJobBuilder simpleJobBuilder(JobBuilderFactory jobBuilderFactory,Step ledgerEntry2VoucherStep) throws Exception {
//        return jobBuilderFactory.get("ledgerEntry2VoucherJob"+System.currentTimeMillis()).incrementer(new RunIdIncrementer()).start(ledgerEntry2VoucherStep);
//    }
//
//    
//    /** 
//     * @return 
//     * @throws Exception 
//     * @throws IOException  
//     */  
//    @Bean  
//    @StepScope  
//    public ItemReader<LedgerEntry> reader(@Value("#{jobParameters[txnDate]}") String txnDate,@Value("#{jobParameters[txnCode]}") String txnCode
//    		,@Value("#{jobParameters[busId]}") String busId,@Value("#{jobParameters[bizCode]}") String bizCode) throws Exception{
//    	
//    	LedgerEntryItemReader ledgerEntryItemReader = SpringContextUtil.getBean("ledgerEntryItemReader");
//    	try {
//			ledgerEntryItemReader.initDate(LocalDate.parse(txnDate, DateTimeFormatter.BASIC_ISO_DATE),txnCode,busId,bizCode);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			throw new Exception(e.getMessage());
//		}
//        return ledgerEntryItemReader;  
//    }  
//      
//  
//  
//    /** 
//     * 处理过程 
//     * @return 
//     */  
//    @Bean  
//    @StepScope  
//    public ItemProcessor<LedgerEntry, Voucher> processor() {  
//        return new LedgerEntry2VoucherItemProcessor();  
//    }  
//  
//    /** 
//     * @return 
//     */  
//    @Bean  
//    @StepScope  
//    public ItemWriter<Voucher> writer() {  
//        return new VoucherItemWriter();  
//    }  
  
}