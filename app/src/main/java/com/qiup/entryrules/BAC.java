package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BAC", description = "Entry rule to join Bachelor of Accountancy")
public class BAC
{
    // Advanced math is additional maths
    // Here english is taken from SPM or O-level level
    private static RuleAttribute bacRuleAttribute;

    public BAC() { bacRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's English") String studentEnglishGrade,
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
                    bacRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If STPM got math subject, check it is credit or not
            if(bacRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Matematik (M)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            bacRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            bacRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // if SPM english no pass, straightaway return false
            // if get through, means english is passed
            if(Objects.equals(studentEnglishGrade, "G"))
            {
                return false;
            }
            // check O-level english got pass or not. if no pass return false
            if(Objects.equals(studentEnglishGrade, "U"))
            {
                return false;
            }

            // if STPM got math subject but not credit, or no math subject at STPM
            if(!bacRuleAttribute.isGotMathSubjectAndCredit())
            {
                // check maths and add maths got credit or not. if no credit return false
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
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
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all students subject check got above at least C+ or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C")
                        && !Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bacRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check maths and english
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    bacRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    bacRuleAttribute.setGotMathSubjectAndCredit();
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
                    bacRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    bacRuleAttribute.setGotMathSubjectAndCredit();
                }
            }

            // if SPM english no pass, straightaway return false
            // if get through, means english is passed
            if(Objects.equals(studentEnglishGrade, "G"))
            {
                return false;
            }
            // check O-level english got pass or not. if no pass return false
            if(Objects.equals(studentEnglishGrade, "U"))
            {
                return false;
            }

            // Minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bacRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bacRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If A-Level got math subject, check it is credit or not
            if(bacRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            bacRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            bacRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // if SPM english no pass, straightaway return false
            // if get through, means english is passed
            if(Objects.equals(studentEnglishGrade, "G"))
            {
                return false;
            }
            // check O-level english got pass or not. if no pass return false
            if(Objects.equals(studentEnglishGrade, "U"))
            {
                return false;
            }

            // if A-level got math subject but not credit, or no math subject at A-level
            if(!bacRuleAttribute.isGotMathSubjectAndCredit())
            {
                // check maths and english at spm or o-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
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
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student subject, check got minimum grade D or not
            // Only minimum grade D then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    bacRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all student's subject, check got maths and english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics") )
                {
                    bacRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    bacRuleAttribute.setGotEnglishSubject();
                }
                if(bacRuleAttribute.isGotMathSubject() && bacRuleAttribute.isGotEnglishSubject())
                {
                    break;
                }
            }

            // if either math or english subject no, return false
            if(!bacRuleAttribute.isGotMathSubject() || !bacRuleAttribute.isGotEnglishSubject())
            {
                //TODO say error
                return false;
            }

            // hHre check english is at least pass(C8) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(Objects.equals(studentGrades[i], "F9"))
                    {
                        return false; // if no return false means english is pass or credit
                    }
                    break;
                }
            }

            // For all student subjects, check the mathematics is got credit(B6) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bacRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check credit is enough or not
        if(bacRuleAttribute.getCountALevel() >= 2
                || bacRuleAttribute.getCountSTAM() >= 1
                || bacRuleAttribute.getCountSTPM() >= 2
                || bacRuleAttribute.getCountUEC() >= 5)
        {
            // if credit is enough, check math is credit or not.
            // If is credit then all requirements satisfied = return true
            if(bacRuleAttribute.isGotMathSubjectAndCredit())
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
        // if rule is statisfied (return true), this action will be executed
        bacRuleAttribute.setJoinProgramme(true);
        Log.d("BACjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bacRuleAttribute.isJoinProgramme();
    }
}
