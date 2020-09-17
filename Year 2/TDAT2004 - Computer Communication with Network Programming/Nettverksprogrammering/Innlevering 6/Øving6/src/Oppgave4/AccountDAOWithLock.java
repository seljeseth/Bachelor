package Oppgave4;

import javax.persistence.*;
import java.util.List;


public class AccountDAOWithLock {
    private static EntityManagerFactory emf;

    public AccountDAOWithLock(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveNewAccount(AccountWithLock account) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }

    //Finds an account based on the primary key (accountNR)
    public AccountWithLock findAccount(long accountNR) {
        EntityManager em = getEM();
        try {
            return em.find(AccountWithLock.class, accountNR);
        } finally {
            closeEM(em);
        }
    }

   //changes an existing account and uses merge so the entity
    public void changeAccount(AccountWithLock account) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            AccountWithLock b = em.merge(account);
            em.getTransaction().commit(); //commit makes sure that the changes are saved in db
        } finally {
            closeEM(em);
        }
    }


    public void deleteAccount(long accountNR) {
        EntityManager em = getEM();
        try {
            AccountWithLock a = findAccount(accountNR);
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }
    public List<AccountWithLock> getAllAccounts() {
        EntityManager em = getEM();
        try {
            Query q = em.createQuery("SELECT OBJECT(o) FROM AccountWithLock o");
            return q.getResultList();
        } finally {
            closeEM(em);
        }
    }

    //gets all accounts with a balance higher than x
    public List<AccountWithLock> getAllAccountsWithBalanceOver(double balance) {
        EntityManager em = getEM();
        try {
            Query q = em.createQuery("SELECT OBJECT(o) FROM AccountWithLock o WHERE o.balance > :balance ");
            q.setParameter("balance",balance);
            return q.getResultList();
        } finally {
            closeEM(em);
        }
    }


    private EntityManager getEM() {
        return emf.createEntityManager();
    }

    private void closeEM(EntityManager em) {
        if (em != null && em.isOpen()) em.close();
    }



    public static void main(String[] args) throws Exception{
        AccountDAOWithLock fasade = null;
        System.out.println("starting...");
        try {

            emf = Persistence.createEntityManagerFactory("oppgave4_accountEntity");
            System.out.println("Constructor done " + emf);
            fasade = new AccountDAOWithLock(emf);
            System.out.println("Constructor done");


            List<AccountWithLock> list = fasade.getAllAccounts();

            AccountWithLock a = list.get(0);
            System.out.println(a.getBalance());
            a.withdraw(100);
            Thread.sleep(5000);
            fasade.changeAccount(a);
            System.out.println(a.getBalance());




        } finally {
            emf.close();
        }
    }
}