package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "Pharmacy", description = "Entry rule to join Bachelor of Pharmacy")
public class Pharmacy
{
    // advanced math is additional maths
    private static RuleAttribute pharmacyRuleAttribute;
    private boolean gotMathSubject, gotMathSubjectAndCredit,
            gotAddMathSubject, gotAddMathSubjectAndCredit,
            gotChemi, gotChemiAndCredit,
            gotBio, gotBioAndCredit,
            gotPhysics, gotPhysicsAndCredit;

    public Pharmacy() {
        pharmacyRuleAttribute = new RuleAttribute();
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
        gotChemi = false;
        gotChemiAndCredit = false;
        gotBio = false;
        gotBioAndCredit = false;
        gotPhysics = false;
        gotPhysicsAndCredit = false;
        gotAddMathSubject = false;
        gotAddMathSubjectAndCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's English") String studentEnglishGrade,
                               @Fact("Student's Bahasa Malaysia") String studentBahasaMalaysiaGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
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
                if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        gotAddMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        gotPhysicsAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Kimia"))
                {
                    if(Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "A-")
                            || Objects.equals(studentGrades[i], "B+")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        gotChemiAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "A-")
                            || Objects.equals(studentGrades[i], "B+")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        gotBioAndCredit = true;
                    }
                }
            }

            if(gotBioAndCredit && gotChemiAndCredit
                    && (gotPhysicsAndCredit || gotAddMathSubjectAndCredit || gotMathSubjectAndCredit))
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "G"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D")
                                && !Objects.equals(studentEnglishGrade, "E")
                                && !Objects.equals(studentEnglishGrade, "G"))
                        {
                            return true;
                        }
                    }
                }
                else if(Objects.equals(studentSPMOLevel, "O-Level"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D7")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E8")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "F9")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "U"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D7")
                                && !Objects.equals(studentEnglishGrade, "E8")
                                && !Objects.equals(studentEnglishGrade, "F9")
                                && !Objects.equals(studentEnglishGrade, "U"))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B") || Objects.equals(studentGrades[i], "C"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B") || Objects.equals(studentGrades[i], "C"))
                    {
                        gotAddMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B") || Objects.equals(studentGrades[i], "C"))
                    {
                        gotPhysicsAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B"))
                    {
                        gotChemiAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B"))
                    {
                        gotBioAndCredit = true;
                    }
                }
            }

            if(gotBioAndCredit && gotChemiAndCredit
                    && (gotPhysicsAndCredit || gotAddMathSubjectAndCredit || gotMathSubjectAndCredit))
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "G"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D")
                                && !Objects.equals(studentEnglishGrade, "E")
                                && !Objects.equals(studentEnglishGrade, "G"))
                        {
                            return true;
                        }
                    }
                }
                else if(Objects.equals(studentSPMOLevel, "O-Level"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D7")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E8")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "F9")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "U"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D7")
                                && !Objects.equals(studentEnglishGrade, "E8")
                                && !Objects.equals(studentEnglishGrade, "F9")
                                && !Objects.equals(studentEnglishGrade, "U"))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject check got mathematics, physic, chemi, bio subject or not
            // add maths is advanced maths
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    gotAddMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    gotChemi = true;
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    gotBio = true;
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    gotPhysics = true;
                }
            }

            if(!gotChemi || !gotBio || !gotPhysics || !gotMathSubject || !gotAddMathSubject)
            {
                return false;
            }

            // for all subject check other subject is at least B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotAddMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotChemiAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotBioAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotPhysicsAndCredit = true;
                    }
                }
            }

            if(gotChemiAndCredit && gotBioAndCredit && gotPhysicsAndCredit && gotMathSubjectAndCredit && gotAddMathSubjectAndCredit)
            {
                return true;
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA, English Proficiency Test
            // FIXME Foundation / Matriculation, Diploma
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        pharmacyRuleAttribute.setJoinProgramme(true);
        Log.d("Pharmacy", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return pharmacyRuleAttribute.isJoinProgramme();
    }
}
