package app;

import data.DataController;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: steffenwitt
 * Date: 02.01.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class TargetPractice {

    String activeTarget;
    TargetHelper activeTargetHelper;
    private ArrayList<TargetHelper> targetHelperList;

    public TargetPractice() {
        targetHelperList = new ArrayList<TargetHelper>();
    }

    public TargetHelper addThrow(int newhits, String s){
        System.out.println("received:"+newhits+","+s);
        if(activeTarget == null && activeTargetHelper == null){
            activeTarget = s;
            activeTargetHelper = new TargetHelper(activeTarget);
            targetHelperList.add(activeTargetHelper);
        }
        if(activeTarget != s){
            boolean targetExists = false;
            for (TargetHelper targetHelper : targetHelperList) {
                if (targetHelper.getTarget().equals(s)){
                    targetExists = true;
                    activeTarget = s;
                    activeTargetHelper = targetHelper;
                    break;
                }
            }
            if(!targetExists){

                activeTarget = s;
                activeTargetHelper = new TargetHelper(activeTarget);
                targetHelperList.add(activeTargetHelper);
            }

        }
        activeTargetHelper.addRound(newhits);
        return activeTargetHelper;

    }


    public void saveGame() {
        DataController.getInstance().saveTPGame(targetHelperList);
        quitGame();
    }

    private void quitGame() {
        activeTarget = null;
        activeTargetHelper = null;
        targetHelperList = new ArrayList<TargetHelper>();
    }
}
