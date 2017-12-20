package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BFI", description = "Entry rule to join Bachelor of Finance")
public class BFI
{
    // advanced maths = additional maths
    private static RuleAttribute bfiRuleAttribute;
    private boolean gotAdvancedMath, advancedMathFail, advancedMathPass, advancedMathCredit,
            mathCredit, mathPass, englishPass,
            gotMathSubject, gotMathSubjectAndCredit, gotEnglishSubject, gotEnglishSubjectAndPass;

    public BFI() {
        bfiRuleAttribute = new RuleAttribute();
        gotAdvancedMath = false;
        advancedMathFail = false;
        advancedMathPass = false;
        advancedMathCredit = false;
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
        mathPass = false;
        mathCredit = false;
        gotEnglishSubjectAndPass = false;
        englishPass = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if (Objects.equals(qualificationLevel, "STPM")) // if qualification is STPM
        {
            // for all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)") || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                {
                    gotEnglishSubject = true;
                }
                if(gotMathSubject && gotEnglishSubject)
                {
                    break;
                }
            }

            if(gotMathSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    //check mathematics is credit or not
                    if(Objects.equals(studentSubjects[i], "Matematik (M)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                }
            }

            if(gotEnglishSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            gotEnglishSubjectAndPass = true;
                        }
                        break;
                    }
                }
            }

            // if stpm got math subject but not credit, or no math subject at STPM
            if(!gotMathSubjectAndCredit)
            {
                // check maths and english
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    // if maths no credit, straightaway return false
                    if(Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                }
                else // if is o-level
                {
                    // check maths got credit or not. if no credit return false
                    if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // if stpm got english subject but not pass, or no english subject at STPM
            if(!gotEnglishSubjectAndPass)
            {
                // check english
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    // if fail, return false
                    if(Objects.equals(studentEnglishGrade, "G"))
                    {
                        return false;
                    }
                }
                else // is o-level
                {
                    // if fail, return false
                    if(Objects.equals(studentEnglishGrade, "E8") || Objects.equals(studentEnglishGrade, "F9") || Objects.equals(studentEnglishGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // minimum C+ only increment
            for (int i = 0; i < studentSubjects.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "C")
                        && !Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F")) {
                    bfiRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "STAM")) // if is STAM
        {
            // check maths and english
            // if maths no credit or english is not pass, return false
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                // check math
                if(Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G"))
                {
                    return false;
                }
                //check english
                if(Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                // check maths
                if(Objects.equals(studentEnglishGrade, "D7") || Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }
                // check english
                if(Objects.equals(studentEnglishGrade, "E8") || Objects.equals(studentEnglishGrade, "F9") || Objects.equals(studentEnglishGrade, "U"))
                {
                    return false;
                }
            }
            // minimum Jayyid only increment
            for (int i = 0; i < studentSubjects.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bfiRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "A-Level")) // if is A-Level
        {
            // for all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Literature in English"))
                {
                    gotEnglishSubject = true;
                }
                if(gotMathSubject && gotEnglishSubject)
                {
                    break;
                }
            }

            if(gotMathSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    //check mathematics is credit or not
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                }
            }

            if(gotEnglishSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Literature in English"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            gotEnglishSubjectAndPass = true;
                        }
                        break;
                    }
                }
            }

            // if stpm got math subject but not credit, or no math subject at STPM
            if(!gotMathSubjectAndCredit)
            {
                // check maths and english at spm or o-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    // if maths no credit, straightaway return false
                    if(Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                }
                else // if is o-level
                {
                    // if maths credit return false
                    if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // if stpm got english subject but not pass, or no english subject at STPM
            if(!gotEnglishSubjectAndPass)
            {
                // check english
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    // if fail, return false
                    if(Objects.equals(studentEnglishGrade, "G"))
                    {
                        return false;
                    }
                }
                else // is o-level
                {
                    // if fail, return false
                    if(Objects.equals(studentEnglishGrade, "E8") || Objects.equals(studentEnglishGrade, "F9") || Objects.equals(studentEnglishGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            //at least grade D only increment
            for (int i = 0; i < studentSubjects.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "F"))
                {
                    bfiRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "UEC")) // if is UEC
        {
            // check got advanced maths and is fail or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    gotAdvancedMath = true; // if got advanced maths, set true
                    if(Objects.equals(studentGrades[i], "F9"))
                    {
                        advancedMathFail = true; // if is fail(F9)
                    }
                    else if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8"))
                    {
                        advancedMathPass= true;
                    }
                    else // above c7
                    {
                        advancedMathCredit = true;
                    }
                    break;
                }
            }

            // if no adv maths
            if(!gotAdvancedMath)
            {
                // for all student subjects, check the mathematics is at credit (C7) or not
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(Objects.equals(studentGrades[i], "F9"))
                        {
                            return false;
                        }
                        else if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") )
                        {
                            mathCredit = true;
                        }
                        break;
                    }
                }
            }
            else // got adv math
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(advancedMathFail || advancedMathPass)
                    {
                        if(Objects.equals(studentSubjects[i], "Mathematics"))
                        {
                            // if both adv math and math no credit or fail, return false
                            if (Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
                            {
                                return false;
                            }
                            else
                            {
                                mathCredit = true;
                            }
                            break;
                        }
                    }
                }
            }

            // here check english is at least pass(C8) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(Objects.equals(studentGrades[i], "F9"))
                    {
                        return false;
                    }
                    else
                    {
                        englishPass = true;
                    }
                    break;
                }
            }

            // for all subject check got at least minimum grade B or not. At least B only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bfiRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA of 2.00 out of 4.00
            // FIXME Foundation / Matriculation
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bfiRuleAttribute.getCountUEC() >= 3)
        {
            if((mathCredit || advancedMathCredit) && englishPass)
            {
                return true;
            }
        }

        if(bfiRuleAttribute.getCountALevel() >= 2 || bfiRuleAttribute.getCountSTAM() >= 1 || bfiRuleAttribute.getCountSTPM() >= 2)
        {
            return true;
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bfiRuleAttribute.setJoinProgramme(true);
        Log.d("BFIjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bfiRuleAttribute.isJoinProgramme();
    }
}
