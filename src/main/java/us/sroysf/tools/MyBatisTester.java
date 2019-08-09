package us.sroysf.tools;

import com.mybatis3.entity.Account;
import com.mybatis3.mappers.AccountMapper;
import com.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyBatisTester {

    private static final Logger log = LoggerFactory.getLogger(MyBatisTester.class);

    public static void main(String[] args) throws Exception {

        demoAccountEntity();
    }

    private static void demoAccountEntity() {
        try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()) {

            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = new Account();
            account.setFirstName("John");
            account.setLastName("Smith");
            account.setEmail("johnsmith@testing.com");
            account.setPassword("abc123xyz567");

            mapper.insertAccount(account);
            log.info("Inserted account, got account id = {}", account.getAccountId());

            account = new Account();
            account.setFirstName("Sally");
            account.setLastName("Jones");
            account.setEmail("sallyj@testing.com");
            account.setPassword("abc123xyz567");
            mapper.insertAccount(account);
            log.info("Inserted account, got account id = {}", account.getAccountId());


            Account foundAccount = mapper.findAccountById(account.getAccountId());
            log.info("Found account = {}", foundAccount);

            log.info("--------- Listing accounts ---------");
            List<Account> allAccounts = mapper.findAllAccounts();
            for (Account acc : allAccounts) {
                log.info("Account: {}", acc);
            }

            // We intentionally do not commit the transaction here, in order to make this demo easily
            // repeatable.
            // sqlSession.commit();

        } catch (Exception ex) {
            throw ex;
        }
    }
}
