

public class ReferanseTest
{
    private Person testObj;

    public ReferanseTest()
    {
        testObj = new Person(1,"Per Person");
    }
    public Person getPerson()
    {
        return testObj;
    }


    class Person
    {
        String lokalNavn;
        Integer lokalId;
        Person(int id, String navn)
        {
            lokalId = id;
            lokalNavn = navn;
        }

        public void setNavn(String str)
        {
            lokalNavn = str;
        }

        public void setId(Integer id)
        {
            lokalId = id;
        }

        public String getNavn()
        {
            return lokalNavn;
        }

        public Integer getId()
        {
            return lokalId;
        }

        public String toString()
        {
            return lokalId + " " + lokalNavn;
        }
    }//end Person

    public static void main(String[] args)
    {
        ReferanseTest refTest = new ReferanseTest();
        Person testPerson = refTest.getPerson();

        System.out.println(testPerson); // 1  per person

        Integer id = testPerson.getId();
        String navn = testPerson.getNavn();

        id = 0;
        navn = "Bør Børson";

        testPerson = refTest.getPerson();

        System.out.println(testPerson); // 1 per person

        testPerson.setId(11);
        testPerson.setNavn("Charlie Chaplin");

        testPerson = refTest.getPerson();

        System.out.println(testPerson); //11 charlie chaplin
    }//end main
}

    //Hva blir resultatet når man kjører koden? Grunngi svaret.