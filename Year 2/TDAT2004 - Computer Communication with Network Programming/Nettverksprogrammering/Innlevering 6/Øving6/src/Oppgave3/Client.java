package Oppgave3;

import Oppgave2.AccountWithoutLock;
import Oppgave2.AccountDAOWithoutLock;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Client {
    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        AccountDAOWithoutLock fasade = null;
        System.out.println("starting...");
        try {
            emf = Persistence.createEntityManagerFactory("oppgave2_accountEntity");
            System.out.println("Constructor done " + emf);
            fasade = new AccountDAOWithoutLock(emf);
            System.out.println("Constructor done");
            List<AccountWithoutLock> list = fasade.getAllAccounts();

            AccountWithoutLock account = list.get(1);
            System.out.println(account.getBalance());
            account.withdraw(100);
            Thread.sleep(5000);
            fasade.changeAccount(account);
            System.out.println(account.getBalance());




        } finally {
            emf.close();
        }
    }
}