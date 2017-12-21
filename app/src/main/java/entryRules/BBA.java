package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BBA", description = "Entry rule to join Bachelor of Business Administration")
public class BBA
{
    // advanced math is additional maths
    private static RuleAttribute bbaRuleAttribute;
    private boolean gotAdvancedMath, advancedMathFail, advancedMathPass, advancedMathCredit,
            mathCredit, mathPass, englishPass, englishCredit,
            gotMathSubject, gotMathSubjectAndPass, gotEnglishSubject, gotEnglishSubjectAndPass;

    public BBA() {
        bbaRuleAttribute = new RuleAttribute();
        gotAdvancedMath = false;
        advancedMathFail = false;
        advancedMathPass = false;
        advancedMathCredit = false;
        gotMathSubject = false;
        gotMathSubjectAndPass = false;
        mathPass = false;
        mathCredit = false;
        gotEnglishSubjectAndPass = false;
        englishPass = false;
        englishCredit = false;
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
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check got mathematics and english subject or not
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
                    if(Objects.equals(studentSubjects[i], "Matematik (M)"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndPass = true;
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndPass = true;
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

            // if stpm got math subject but not pass, or no math subject at STPM
            if(!gotMathSubjectAndPass)
            {
                // check maths
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    // if fail, return false
                    if(Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                }
                else // is o-level
                {
                    // if fail, return false
                    if(Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
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

            // for all students subject check got above at least C or not. At least C only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bbaRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check maths and english
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(Objects.equals(studentMathematicsGrade, "G") || Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                // check maths
                if(Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }
                // check english
                if(Objects.equals(studentEnglishGrade, "E8") || Objects.equals(studentEnglishGrade, "F9") || Objects.equals(studentEnglishGrade, "U"))
                {
                    return false;
                }
            }

            // minimum grade of Jayyid, only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bbaRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check got mathematics and english subject or not
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
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndPass = true;
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndPass = true;
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
            // if a-level got math subject but not pass, or no math subject at a-level
            if(!gotMathSubjectAndPass)
            {
                // check maths
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    // if fail return false
                    if(Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                }
                else // is o-level
                {
                    // if fail return false
                    if(Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // if a-level got english subject but not pass, or no eng subject at a-level
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

            // for all student subject, check got minimum grade E (pass). At least E only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "F"))
                {
                    bbaRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
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
                // for all student subjects, check the mathematics is at least pass(C8) or not
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(Objects.equals(studentGrades[i], "F9"))
                        {
                            return false;
                        }
                        else if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") )
                        {
                            mathPass = true;
                        }
                        else // above c7
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
                    if(advancedMathFail)
                    {
                        if(Objects.equals(studentSubjects[i], "Mathematics"))
                        {
                            // if both adv math and math fail, return false
                            if (Objects.equals(studentGrades[i], "F9"))
                            {
                                return false;
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
                    else if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") )
                    {
                        englishPass = true;
                    }
                    else // above c7
                    {
                        englishCredit = true;
                    }
                    break;
                }
            }

            // for all subject check got at least minimum grade B or not. At least B only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bbaRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO minimum CGPA of 2.00 out of 4.00
            //FIXME Foundation / Matriculation
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bbaRuleAttribute.getCountUEC() >= 3)
        {
            if((advancedMathPass || mathPass) && (englishPass || englishCredit))
            {
                return true;
            }
            else if((advancedMathCredit || mathCredit) && (englishPass || englishCredit))
            {
                return true;
            }
            if(englishPass && mathPass)
            {
                return true;
            }
            else if(englishCredit && mathCredit)
            {
                return true;
            }
        }

        if(bbaRuleAttribute.getCountALevel() >= 2 || bbaRuleAttribute.getCountSTAM() >= 1 || bbaRuleAttribute.getCountSTPM() >= 2)
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
        bbaRuleAttribute.setJoinProgramme(true);
        Log.d("BBAjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbaRuleAttribute.isJoinProgramme();
    }
}
