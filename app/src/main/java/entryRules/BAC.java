package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;


@Rule(name = "BAC", description = "Entry rule to join Bachelor of Accountancy")
public class BAC
{
    // advanced math is additional maths
    private static RuleAttribute bacRuleAttribute;
    private boolean gotAdvancedMath, advancedMathFail, advancedMathCredit,
            mathCredit, gotMathSubject, gotMathSubjectAndCredit;

    public BAC() {
        bacRuleAttribute = new RuleAttribute();
        gotAdvancedMath = false;
        advancedMathFail = false;
        advancedMathCredit = false;
        mathCredit = false;
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
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
            // for all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)") || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    gotMathSubject = true;
                    break;
                }
            }

            if(gotMathSubject)
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

            // if SPM english no pass, straightaway return false
            if(Objects.equals(studentEnglishGrade, "G"))
            {
                return false;
            }
            // check O-level english got pass or not. if no pass return false
            if(Objects.equals(studentEnglishGrade, "E8") || Objects.equals(studentEnglishGrade, "F9") || Objects.equals(studentEnglishGrade, "U"))
            {
                return false;
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

            // for all students subject check got above at least C+ or not
            for(int i = 0; i < studentSubjects.length; i++)
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
                // if maths no credit, straightaway return false
                if(Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G"))
                {
                    return false;
                }
                // if english no pass, straightaway return false
                if(Objects.equals(studentEnglishGrade, "G"))
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
                // check english got pass or not. if no pass return false
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
                    bacRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    gotMathSubject = true;
                    break;
                }
            }

            if(gotMathSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
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

            // if SPM english no pass, straightaway return false
            if(Objects.equals(studentEnglishGrade, "G"))
            {
                return false;
            }
            // check O-level english got pass or not. if no pass return false
            if(Objects.equals(studentEnglishGrade, "E8") || Objects.equals(studentEnglishGrade, "F9") || Objects.equals(studentEnglishGrade, "U"))
            {
                return false;
            }

            // if A-level got math subject but not credit, or no math subject at A-level
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
                    // if math no credit return false
                    if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // for all student subject, check got minimum grade D
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "F"))
                {
                    bacRuleAttribute.incrementCountALevel(1);
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
                    if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
                    {
                        //if advanced math at least not grade B, set advancedMathFail to true
                        advancedMathFail = true;
                    }
                    else
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
                        if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
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
            else // got adv math
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(advancedMathFail)
                    {
                        if(Objects.equals(studentSubjects[i], "Mathematics"))
                        {
                            // if both adv math and math at least not grade B, return false
                            if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
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
                        return false; // if no return false means english is pass or credit
                    }
                    break;
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bacRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO minimum CGPA of 2.00 out of 4.00
            // FIXME Foundation / Matriculation
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bacRuleAttribute.getCountUEC() >= 3)
        {
            if(mathCredit || advancedMathCredit)
            {
                return true;
            }
        }

        if(bacRuleAttribute.getCountALevel() >= 2 || bacRuleAttribute.getCountSTAM() >= 1 || bacRuleAttribute.getCountSTPM() >= 2)
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
        bacRuleAttribute.setJoinProgramme(true);
        Log.d("BACjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bacRuleAttribute.isJoinProgramme();
    }
}
