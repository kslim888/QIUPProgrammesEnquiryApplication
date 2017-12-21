package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "MassCommAdvertising", description = "Entry rule to join Bachelor of Mass Communication Advertising")
public class MassCommAdvertising
{
    // advanced math is additional maths
    private static RuleAttribute advertisingRuleAttribute;
    private boolean gotEnglishSubject, gotEnglishSubjectAndCredit,
            gotEnglishSubjectAndPass, englishCredit;

    public MassCommAdvertising() {
        advertisingRuleAttribute = new RuleAttribute();
        gotEnglishSubjectAndCredit = false;
        gotEnglishSubjectAndPass = false; // for a-level
        englishCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check got english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                {
                    gotEnglishSubject = true;
                    break;
                }
            }

            // if got eng subject, check it is at credit or not
            if(gotEnglishSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            gotEnglishSubjectAndCredit = true;
                        }
                        break;
                    }
                }
            }

            // if stpm got english subject but not credit, or no english subject at STPM
            if(!gotEnglishSubjectAndCredit)
            {
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    // if is not credit, return false
                    if(Objects.equals(studentEnglishGrade, "D")
                            || Objects.equals(studentEnglishGrade, "E")
                            || Objects.equals(studentEnglishGrade, "G"))
                    {
                        return false;
                    }
                }
                else // is o-level
                {
                    // if is not credit, return false
                    if(Objects.equals(studentEnglishGrade, "D7")
                            || Objects.equals(studentEnglishGrade, "E8")
                            || Objects.equals(studentEnglishGrade, "F9")
                            || Objects.equals(studentEnglishGrade, "U"))
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
                    advertisingRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check english
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                // if is not credit, return false
                if(Objects.equals(studentEnglishGrade, "D")
                        || Objects.equals(studentEnglishGrade, "E")
                        || Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                // if is not credit, return false
                if(Objects.equals(studentEnglishGrade, "D7")
                        || Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentEnglishGrade, "F9")
                        || Objects.equals(studentEnglishGrade, "U"))
                {
                    return false;
                }
            }

            // minimum grade of Jayyid, only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    advertisingRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Literature in English"))
                {
                    gotEnglishSubject = true;
                    break;
                }
            }

            // if got eng subject, check it is at least pass or not
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

            // if a-level got english subject but not credit or pass, or no english subject at a-level
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

            // for all student subject, check got minimum grade C. At least C only increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D") && !Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "F"))
                {
                    advertisingRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // here check english is at least pass(C8) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(Objects.equals(studentGrades[i], "F9"))
                    {
                        return false;
                    }
                    else if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") )
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
                    advertisingRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO minimum CGPA of 2.00 out of 4.00
            //FIXME Foundation / Matriculation
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(advertisingRuleAttribute.getCountUEC() >= 4)
        {
            if(englishCredit)
            {
                return true;
            }
        }

        if(advertisingRuleAttribute.getCountALevel() >= 2 || advertisingRuleAttribute.getCountSTAM() >= 1 || advertisingRuleAttribute.getCountSTPM() >= 2)
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
        advertisingRuleAttribute.setJoinProgramme(true);
        Log.d("MassCommAdvertising", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return advertisingRuleAttribute.isJoinProgramme();
    }

}
