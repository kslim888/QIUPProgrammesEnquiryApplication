package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BIS", description = "Entry rule to join Bachelor of Business Information System")
public class BIS
{
    private static RuleAttribute bisRuleAttribute;
    public BIS() { bisRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bisRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If STPM got math subject, check it is credit or not
            if(bisRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Matematik (M)")
                            || Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            bisRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // If STPM got math subject but not credit, or no math subject
            // check SPM / O-Level math is credit or not
            if(!bisRuleAttribute.isGotMathSubjectAndCredit())
            {
                // check maths and english
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all students subject check  at least C or not. At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bisRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bisRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If A-Level" got math subject, check it is credit or not
            if(bisRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics")
                            || Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            bisRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // if A-level got math subject but not credit, or no math subject at A-level
            // check SPM / O-Level math is credit or not
            if(!bisRuleAttribute.isGotMathSubjectAndCredit())
            {
                // check maths and english at spm or o-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // for all student subject, check got minimum pass E. At least E(pass) only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "U"))
                {
                    bisRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // Check got maths and is credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    bisRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If no math subject, straight return false
            if(!bisRuleAttribute.isGotMathSubject())
            {
                return false;
            }

            // For all student subjects, check the mathematics is at least credit(B6) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bisRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not. At least B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bisRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check is enough credit or not. if is enough credit, check math is credit or not
        // If both true, return true as all requirements satisfied
        if(bisRuleAttribute.getUecCredit() >= 5
                || bisRuleAttribute.getALevelCredit() >= 2
                || bisRuleAttribute.getStpmCredit() >= 2
                || bisRuleAttribute.getUecCredit() >= 5)
        {
            if(bisRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        // If requirements not satisfied, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        bisRuleAttribute.setJoinProgrammeTrue();
        Log.d("BIS", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bisRuleAttribute.isJoinProgramme();
    }
}

