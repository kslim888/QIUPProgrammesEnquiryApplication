package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Arrays;
import java.util.List;

@Rule(name = "FIB/FIA", description = "Entry rule to join Foundation in Business or Foundation in Arts")
public class FIB
{
    // rule attribute
    List<String> programmeNameList = Arrays.asList("FIB", "FIA"),
            resultsTypeList = Arrays.asList("SPM", "O-Level", "UEC");
    int numberOfCreditNeeded  = 5;
    boolean joinProgramme = false;
    RuleAttribute ruleAttribute;

    // when
    // receive parameter for how many credit, which results type\
    // dont check for programme name. because wan see all programme qualify or not
    @Condition
    public boolean allowToJoin(@Fact("Number of Credit") int number, @Fact("Results Type") String resultsType)
    {
        if(resultsType.matches(resultsTypeList.get(2))) // for uec
        {
            if(number >= 3)
            {
                joinProgramme = true;
            }
        }
        else // for SPM and O-Level
        {
            if(number >= numberOfCreditNeeded)
            {
                   joinProgramme = true;
            }
        }
        joinProgramme =  false;
        if(!isJoinProgramme())
        {
            Log.d("myTag", "Not joined");
        }
        return joinProgramme;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        Log.d("myTag", "Joined");
        setJoinProgrammen(true);
    }

    public boolean isJoinProgramme()
    {
        return joinProgramme;
    }

    public void setJoinProgrammen(boolean joinProgramme) {
        this.joinProgramme = joinProgramme;
    }

}
