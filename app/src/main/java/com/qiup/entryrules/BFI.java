package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BFI", description = "Entry rule to join Bachelor of Finance")
public class BFI
{
    // Advanced maths = additional maths
    private static RuleAttribute bfiRuleAttribute;

    public BFI() { bfiRuleAttribute = new RuleAttribute(); }

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
        if (Objects.equals(qualificationLevel, "STPM")) // if qualification is STPM
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bfiRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                {
                    bfiRuleAttribute.setGotEnglishSubject();
                }
                if(bfiRuleAttribute.isGotMathSubject() && bfiRuleAttribute.isGotEnglishSubject())
                {
                    break;
                }
            }

            // If STPM got math subject, check it is credit or not
            if(bfiRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    //check mathematics is credit or not
                    if(Objects.equals(studentSubjects[i], "Matematik (M)")
                            || Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            bfiRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // If STPM got english subject, check it is credit or not
            if(bfiRuleAttribute.isGotEnglishSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            bfiRuleAttribute.setGotEnglishSubjectAndPass();
                        }
                        break;
                    }
                }
            }

            // if STPM got math subject but not credit, or no math subject at STPM
            if(!bfiRuleAttribute.isGotMathSubjectAndCredit())
            {
                // check maths at SPM or O-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bfiRuleAttribute.setGotMathSubject();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bfiRuleAttribute.setGotMathSubject();
                    }
                }
                else // if is O-level
                {
                    if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bfiRuleAttribute.setGotMathSubject();
                    }
                    else if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bfiRuleAttribute.setGotMathSubject();
                    }
                }
            }

            // if STPM got english subject but not pass, or no english subject
            if(!bfiRuleAttribute.isGotEnglishSubjectAndPass())
            {
                // Check english at SPM or O-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentEnglishGrade, "G"))
                    {
                        bfiRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
                else // is O-Level
                {
                    if(!Objects.equals(studentEnglishGrade, "U"))
                    {
                        bfiRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // Minimum C+ only increment
            for (int i = 0; i < studentGrades.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "C")
                        && !Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F")) {
                    bfiRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "STAM")) // if is STAM
        {
            // check add maths, math and english at SPM or O-Level
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                // Check add math and math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    bfiRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    bfiRuleAttribute.setGotMathSubjectAndCredit();
                }

                // Check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    bfiRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is O-Level
            {
                // Check maths
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    bfiRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    bfiRuleAttribute.setGotMathSubjectAndCredit();
                }

                // check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    bfiRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            // minimum Jayyid only increment
            for (int i = 0; i < studentGrades.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "Maqbul")
                        && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bfiRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "A-Level")) // if is A-Level
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bfiRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Literature in English"))
                {
                    bfiRuleAttribute.setGotEnglishSubject();
                }
                if(bfiRuleAttribute.isGotMathSubject() && bfiRuleAttribute.isGotEnglishSubject())
                {
                    break;
                }
            }

            // If A-Level got mathematic subject, check it is credit or not
            if(bfiRuleAttribute.isGotEnglishSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    //check mathematics or add math is credit or not
                    if(Objects.equals(studentSubjects[i], "Mathematics")
                            || Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            bfiRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // If A-Level got english subject, check it is credit or not
            if(bfiRuleAttribute.isGotEnglishSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Literature in English"))
                    {
                        if(!Objects.equals(studentGrades[i], "U"))
                        {
                            bfiRuleAttribute.setGotEnglishSubjectAndPass();
                        }
                        break;
                    }
                }
            }

            // if A-level got math subject but not credit, or no math subject at A-Level
            if(!bfiRuleAttribute.isGotMathSubjectAndCredit())
            {
                // check maths and add maths at spm or o-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bfiRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bfiRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is O-Level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bfiRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bfiRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // if A-Level got english subject but not pass, or no english subject at A-Level
            if(!bfiRuleAttribute.isGotEnglishSubjectAndPass())
            {
                // check english
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    if(!Objects.equals(studentEnglishGrade, "G"))
                    {
                        bfiRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
                else // is O-level
                {
                    if(!Objects.equals(studentEnglishGrade, "U"))
                    {
                        bfiRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            //at least grade D only increment
            for (int i = 0; i < studentGrades.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "U"))
                {
                    bfiRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "UEC")) // if is UEC
        {
            // Check got maths and english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    bfiRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    bfiRuleAttribute.setGotEnglishSubject();
                }
                if(bfiRuleAttribute.isGotMathSubject() && bfiRuleAttribute.isGotEnglishSubject())
                {
                    break;
                }
            }

            // if either 1 no, return false
            if(!bfiRuleAttribute.isGotMathSubject() || !bfiRuleAttribute.isGotEnglishSubject())
            {
                return false;
            }

            // for all student subjects, check the Mathematics is at credit (B6) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics") )
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bfiRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "F9"))
                    {
                        bfiRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not.
            // At least B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bfiRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // If enough credit, check english pass and math is credit or not
        if(bfiRuleAttribute.getALevelCredit() >= 2
                || bfiRuleAttribute.getStamCredit() >= 1
                || bfiRuleAttribute.getStpmCredit() >= 2
                || bfiRuleAttribute.getUecCredit() >= 5)
        {
            // if english is pass and math is credit, return true as all requirements satisfied
            if(bfiRuleAttribute.isGotEnglishSubjectAndPass() && bfiRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        // If requirements not satisfy, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bfiRuleAttribute.setJoinProgrammeTrue();
        Log.d("BFIjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bfiRuleAttribute.isJoinProgramme();
    }
}
