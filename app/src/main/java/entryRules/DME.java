package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DME", description = "Entry rule to join Diploma in Mechatronics Engineering")
public class DME
{
    //advanced math = additional math for UEC
    private static RuleAttribute dmeRuleAttribute;
    private boolean gotMathSubjectAndCredit, gotEnglishSubjectAndPass, scienceTechnicalVocationalSubjectsCredit;
    public DME() {
        dmeRuleAttribute = new RuleAttribute();
        gotMathSubjectAndCredit = false;
        gotEnglishSubjectAndPass = false;
        scienceTechnicalVocationalSubjectsCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if(Objects.equals(qualificationLevel, "SPM")) // if is STPM qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "G"))
                    {
                        gotEnglishSubjectAndPass = true;
                    }
                }
                if( Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Sains Pertanian")
                        || Objects.equals(studentSubjects[i], "Ekonomi Rumah Tangga")
                        || Objects.equals(studentSubjects[i], "Lukisan Kejuruteraan")
                        || Objects.equals(studentSubjects[i], "Pengajian Kejuruteraan Mekanikal")
                        || Objects.equals(studentSubjects[i], "Pengajian Kejuruteraan Awam")
                        || Objects.equals(studentSubjects[i], "Pengajian Kejuruteraan Elektrik dan Elektronik")
                        || Objects.equals(studentSubjects[i], "Reka Cipta")
                        || Objects.equals(studentSubjects[i], "Teknologi Kejuruteraan")
                        || Objects.equals(studentSubjects[i], "Grafik Komunikasi Teknikal")
                        || Objects.equals(studentSubjects[i], "Pembinaan Domestik")
                        || Objects.equals(studentSubjects[i], "Membuat Perabot")
                        || Objects.equals(studentSubjects[i], "Kerja Paip Domestik")
                        || Objects.equals(studentSubjects[i], "Pendawaian Domestik")
                        || Objects.equals(studentSubjects[i], "Kimpalan Arka dan Gas")
                        || Objects.equals(studentSubjects[i], "Menservis Automobil")
                        || Objects.equals(studentSubjects[i], "Menservis Motosikal")
                        || Objects.equals(studentSubjects[i], "Menservis Peralatan Penyejukan dan Penyamanan Udara")
                        || Objects.equals(studentSubjects[i], "Menservis Peralatan Elektrik Domestik")
                        || Objects.equals(studentSubjects[i], "Rekaan dan Jahitan Pakaian")
                        || Objects.equals(studentSubjects[i], "Katering dan Penyajian")
                        || Objects.equals(studentSubjects[i], "Pemprosesan Makanan")
                        || Objects.equals(studentSubjects[i], "Penjagaan Muka dan Dandanan Rambut")
                        || Objects.equals(studentSubjects[i], "Asuhan dan Pendidikan Awal Kanak-Kanak")
                        || Objects.equals(studentSubjects[i], "Gerontologi Asas dan Perkhidmatan Geriatrik")
                        || Objects.equals(studentSubjects[i], "Landskap dan Nurseri")
                        || Objects.equals(studentSubjects[i], "Akuakultur dan Haiwan Rekreasi")
                        || Objects.equals(studentSubjects[i], "Tanaman Makanan")
                        || Objects.equals(studentSubjects[i], "Seni Reka Tanda")
                        || Objects.equals(studentSubjects[i], "Hiasan Dalaman Asas")
                        || Objects.equals(studentSubjects[i], "Produksi Multimedia")
                        || Objects.equals(studentSubjects[i], "Grafik Berkomputer"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        scienceTechnicalVocationalSubjectsCredit = true;
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    dmeRuleAttribute.incrementCountSPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D7")
                        && !Objects.equals(studentGrades[i], "E8")
                        && !Objects.equals(studentGrades[i], "F9")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dmeRuleAttribute.incrementCountOLevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    dmeRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dmeRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "F9"))
                    {
                        gotEnglishSubjectAndPass = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }

                if(Objects.equals(studentSubjects[i], "Digital Logic")
                        || Objects.equals(studentSubjects[i], "Basic Circuit Theory")
                        || Objects.equals(studentSubjects[i], "Principal Electronic")
                        || Objects.equals(studentSubjects[i], "Fundamentals of Electrical Engineering")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Industrial English")
                        || Objects.equals(studentSubjects[i], "Car Repairing")
                        || Objects.equals(studentSubjects[i], "Industrial Arts"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        scienceTechnicalVocationalSubjectsCredit = true;
                    }
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    dmeRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }


        if(dmeRuleAttribute.getCountUEC() >= 3 || dmeRuleAttribute.getCountSPM() >= 3)
        {
            if(gotEnglishSubjectAndPass && gotMathSubjectAndCredit && scienceTechnicalVocationalSubjectsCredit)
            {
                return true;
            }
        }

        if(dmeRuleAttribute.getCountSTAM() >= 1
                || dmeRuleAttribute.getCountSTPM() >= 1
                || dmeRuleAttribute.getCountALevel() >= 1)
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
        dmeRuleAttribute.setJoinProgramme(true);
        Log.d("DiplMechaEngineering", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dmeRuleAttribute.isJoinProgramme();
    }
}
