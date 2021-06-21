import org.jpl7.*;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom("/Users/ivanlikhvatov/University/ThirdCourse/SecondSemestr/ErmakovFaiphel/projects/dialog-system/src/main/resources/prolog/test.pl")}
                );

        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));


        Variable X = new Variable("X");


        Query q4 =
                new Query(
                        "eliza",
                        new Term[] {new Atom("индивидульность sjlfd sdkjl"), X}
                );

        Map<String, Term> solutions1 = q4.oneSolution();

        System.out.println(solutions1.values());

//        for (Map<String, Term> solution : solutions1) {
//            System.out.println(solution.values());
//        }

        String[] strings = {"a1", "a2"};

        Term rowsTerm = Util.stringArrayToList(strings);

        Query q5 =
                new Query(
                        "add_new_association",
                        new Term[] {new Atom("artyom"), rowsTerm}
                );

        q5.allSolutions();

        Query q6 =
                new Query(
                        "eliza",
                        new Term[] {new Atom("ivan"), X}
                );

        Map<String, Term>[] solutions2 = q6.allSolutions();

        for (Map<String, Term> solution : solutions2) {
            System.out.println(solution.values());
        }

    }

}
