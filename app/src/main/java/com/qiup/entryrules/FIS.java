package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Arrays;
import java.util.Objects;

@Rule(name = "FIS", description = "Entry rule to join Foundation in Sciences")
public class FIS
{
    private static RuleAttribute fisRuleAttribute;

    public FIS() { fisRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "SPM")) // if qualification level is SPM
        {
            // Checking the students is science stream or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    fisRuleAttribute.incrementCountRequiredScienceSubject();
                }
                if(fisRuleAttribute.getCountRequiredScienceSubject() > 2) // means max 3
                {
                    break;
                }
            }

            if(fisRuleAttribute.getCountRequiredScienceSubject() >= 2)
            {
                // Here increment count pass means credit
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Chemistry")
                            || Objects.equals(studentSubjects[i], "Biology")
                            || Objects.equals(studentSubjects[i], "Physics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "G"))
                        {
                            fisRuleAttribute.incrementCountPassScienceSubjects();
                        }
                    }
                }
            }

            // If either eng or bm fail, return false straight away
            for(int i = 0; i < studentSubjects.length; i++) {
                if (Objects.equals(studentSubjects[i], "English")
                        || Objects.equals(studentSubjects[i], "Bahasa Malaysia"))
                {
                    if (Objects.equals(studentGrades[i], "G"))
                    {
                        return false;
                    }
                }
            }

            for(int i = 0; i < studentSubjects.length; i++) {
                // Check add maths or maths got credit or not
                if (Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        fisRuleAttribute.setGotMathSubjectAndCredit();
                        break;
                    }
                }
            }

            // Check all grades. Only C and above increment credit
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    fisRuleAttribute.incrementFoundationCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // If qualification level is O-Level
        {
            // Must got Malay at O-Level
            if(!Arrays.asList(studentSubjects).contains("Malay - Foreign Language"))
            {
                return false;
            }

            // If either eng or bm fail, return false straight away
            for(int i = 0; i < studentSubjects.length; i++) {
                if (Objects.equals(studentSubjects[i], "English")
                        || Objects.equals(studentSubjects[i], "English - First Language")
                        || Objects.equals(studentSubjects[i], "Malay - Foreign Language"))
                {
                    if (Objects.equals(studentGrades[i], "G"))
                    {
                        return false;
                    }
                }
            }

            // Checking the students is science stream or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    fisRuleAttribute.incrementCountRequiredScienceSubject();
                }
                if(fisRuleAttribute.getCountRequiredScienceSubject() > 2) // means max 3
                {
                    break;
                }
            }

            if(fisRuleAttribute.getCountRequiredScienceSubject() >= 2)
            {
                // Here increment count pass means credit
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if (Objects.equals(studentSubjects[i], "Chemistry")
                            || Objects.equals(studentSubjects[i], "Biology")
                            || Objects.equals(studentSubjects[i], "Physics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "F")
                                && !Objects.equals(studentGrades[i], "G")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            fisRuleAttribute.incrementCountPassScienceSubjects();
                        }
                    }
                }
            }

            for(int i = 0; i < studentSubjects.length; i++) {
                // Check add maths or maths got credit or not
                if (Objects.equals(studentSubjects[i], "Mathematics - Additional")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F")
                            && !Objects.equals(studentGrades[i], "G")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        fisRuleAttribute.setGotMathSubjectAndCredit();
                        break;
                    }
                }
            }

            // Check all grades. Only C and above increment credit
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    fisRuleAttribute.incrementFoundationCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // If qualification level is UEC
        {
            // For all students subject check got mathematics, physic, chemi, bio subject or not
            // Add maths is advanced maths
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    fisRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    fisRuleAttribute.setGotChemi();
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    fisRuleAttribute.setGotBio();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    fisRuleAttribute.setGotPhysics();
                }
            }

            // if no chemi and bio, straight return false
            // if no Physics and Mathematics, return false. If either 1 got, then continue...
            if(!fisRuleAttribute.isGotChemi()
                    || !fisRuleAttribute.isGotBio()
                    || (!fisRuleAttribute.isGotMathSubject() && !fisRuleAttribute.isGotChemi()))
            {
                return false;
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        fisRuleAttribute.incrementFoundationCredit();
                    }
                }
            }
        }

        // For UEC, credit required is 3 only
        if(Objects.equals(qualificationLevel, "UEC"))
        {
            if(fisRuleAttribute.getFoundationCredit() >= 3)
            {
                return true;
            }
        }
        else // is SPM or O-Level
        {
            // For both SPM or O-Level, check credit is at least 5 or not
            if(fisRuleAttribute.getFoundationCredit() >= 5)
            {
                // If credit is at least 5, check required subject got at least2 or not
                if(fisRuleAttribute.getCountRequiredScienceSubject() >= 2)
                {
                    // If required subject got at least 2, check it math is credit or not
                    // and Science subject is at least 2 credit or not
                    // If all true, return true as requirements satisfied
                    if(fisRuleAttribute.getCountPassScienceSubjects() >= 2
                            && fisRuleAttribute.isGotMathSubjectAndCredit())
                    {
                        return true;
                    }
                }
            }
        }
        // Return false as requirements not satiesfied
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        fisRuleAttribute.setJoinProgrammeTrue();
        Log.d("FISjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return fisRuleAttribute.isJoinProgramme();
    }
}
