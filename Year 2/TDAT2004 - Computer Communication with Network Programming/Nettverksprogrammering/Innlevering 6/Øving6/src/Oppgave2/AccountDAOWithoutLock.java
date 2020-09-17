package Oppgave2;

import javax.persistence.*;
import java.util.List;


public class AccountDAOWithoutLock {
    private static EntityManagerFactory emf;

    public AccountDAOWithoutLock(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveNewAccount(AccountWithoutLock account) {
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
    public AccountWithoutLock findAccount(long accountNR) {
        EntityManager em = getEM();
        try {
            return em.find(AccountWithoutLock.class, accountNR);
        } finally {
            closeEM(em);
        }
    }

   //changes an existing account and uses merge so the entity
    public void changeAccount(AccountWithoutLock account) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            AccountWithoutLock b = em.merge(account);
            em.getTransaction().commit(); //commit makes sure that the changes are saved in db
        } finally {
            closeEM(em);
        }
    }


    public void deleteAccount(long accountNR) {
        EntityManager em = getEM();
        try {
            AccountWithoutLock a = findAccount(accountNR);
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }
    public List<AccountWithoutLock> getAllAccounts() {
        EntityManager em = getEM();
        try {
            Query q = em.createQuery("SELECT OBJECT(o) FROM AccountWithoutLock o");
            return q.getResultList();
        } finally {
            closeEM(em);
        }
    }

    //gets all accounts with a balance higher than x
    public List<AccountWithoutLock> getAllAccountsWithBalanceOver(double balance) {
        EntityManager em = getEM();
        try {
            Query q = em.createQuery("SELECT OBJECT(o) FROM AccountWithoutLock o WHERE o.balance > :balance ");
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
        AccountDAOWithoutLock fasade = null;
        System.out.println("starting...");
        try{
            emf = Persistence.createEntityManagerFactory("oppgave2_accountEntity");
            System.out.println("Constructor done " + emf);
            fasade = new AccountDAOWithoutLock(emf);
            System.out.println("Constructor done");

            //shows two different ways of creating an account
            //1
            AccountWithoutLock account = new AccountWithoutLock();
            account.setAccountNR(1111111111);
            account.setBalance(3000);
            account.setOwner("Sabine");
            fasade.saveNewAccount(account);

            //2
            account = new AccountWithoutLock(22222222,55,"Sivert");
            fasade.saveNewAccount(account);

            double x = 0;
            System.out.println("Accounts over " + x + " in the database:");
            List<AccountWithoutLock> list = fasade.getAllAccountsWithBalanceOver(x);
            for (AccountWithoutLock a : list){
                System.out.println("---" + a);
            }

            AccountWithoutLock a = list.get(0);
            System.out.println("Account before changing name of owner:\n" + a);
            a.setOwner("Harry Potter");
            fasade.changeAccount(a);
            //shows that the name of the account is altered
            System.out.println("Account after changing name of owner:\n" + fasade.findAccount(a.getAccountNR()));

        }finally{
            emf.close();
        }
    }
}