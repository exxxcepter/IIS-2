import java.util.ArrayList;
import java.util.Scanner;

public class IIS {
    ArrayList<Subject> buildings;
    ArrayList<String> tasks = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<String> goals = new ArrayList<String>();
    ArrayList<String> confirms = new ArrayList<String>();

    public static void main(String[] args){
        IIS starter = new IIS();
        starter.run();
    }

    public void run(){
        tasks.add("Защита путей");
        tasks.add("Иная задача");

        types.add("Мост");
        types.add("Горное сооружение");
        types.add("Другое");

        goals.add("Движение транспорта");     //0
        goals.add("Водозащитное сооружение"); //1
        goals.add("Специальное сооружение");  //2

        confirms.add("На пересечении дорог");     //0
        confirms.add("Возвышение для въезда");    //1
        confirms.add("Пересечение ущелий");       //2
        confirms.add("Пересечение с водоводом");  //3
        confirms.add("Преодоление водотока");     //4
        confirms.add("Задержка воды");            //5
        confirms.add("Пропуск воды под путями");  //6
        confirms.add("Движение сквозь гору");     //7
        confirms.add("Открытый вид тоннеля");     //8
        confirms.add("Защита от селя");           //9
        confirms.add("Защита от обвала");         //10

        Subject puteprovod = new Subject("Путепровод", "Иная задача", "Мост");
        puteprovod.goal.add(goals.get(0));
        puteprovod.confirmation.add(confirms.get(0));

        Subject estakada = new Subject("Эстакада", "Иная задача", "Мост");
        estakada.goal.add(goals.get(0));
        estakada.confirmation.add(confirms.get(1));

        Subject viaduk = new Subject("Виадук", "Иная задача", "Мост");
        viaduk.goal.add(goals.get(0));
        viaduk.confirmation.add(confirms.get(2));

        Subject akveduk = new Subject("Акведук", "Иная задача", "Мост");
        akveduk.goal.add(goals.get(0));
        akveduk.confirmation.add(confirms.get(3));

        Subject truba = new Subject("Водопропускная труба", "Защита путей", "Другое");
        truba.goal.add(goals.get(1));
        truba.confirmation.add(confirms.get(4));

        Subject nasyp = new Subject("Фильтрующая насыпь", "Защита путей", "Другое");
        nasyp.goal.add(goals.get(1));
        nasyp.confirmation.add(confirms.get(5));

        Subject duker = new Subject("Дюкер", "Иная задача", "Другое");
        duker.goal.add(goals.get(2));
        duker.confirmation.add(confirms.get(6));

        Subject tunnel = new Subject("Тоннель", "Иная задача", "Горное сооружение");
        tunnel.goal.add(goals.get(0));
        tunnel.confirmation.add(confirms.get(7));

        Subject galery = new Subject("Галерея", "Иная задача", "Горное сооружение");
        galery.goal.add(goals.get(0));
        galery.confirmation.add(confirms.get(8));

        Subject selespusk = new Subject("Селеспуск", "Защита путей", "Горное сооружение");
        selespusk.goal.add(goals.get(2));
        selespusk.confirmation.add(confirms.get(9));

        Subject stena = new Subject("Подпорная стена", "Защита путей", "Горное сооружение");
        stena.goal.add(goals.get(2));
        stena.confirmation.add(confirms.get(10));

        buildings = new ArrayList<Subject>();
        buildings.add(puteprovod);
        buildings.add(estakada);
        buildings.add(viaduk);
        buildings.add(akveduk);
        buildings.add(truba);
        buildings.add(nasyp);
        buildings.add(duker);
        buildings.add(tunnel);
        buildings.add(galery);
        buildings.add(selespusk);
        buildings.add(stena);

        System.out.println("Курсовая работа не тему:");
        System.out.println("\"Искусственные сооружения\"");
        chooseTask(0);
    }

    public boolean chooseTask(int taskIndex){
        if(taskIndex >= tasks.size()){
            System.out.println("Нет результатов!");
            return false;
        }
        System.out.println(tasks.get(taskIndex) + "?");
        System.out.print("y/n: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.compareTo("y") == 0){
            ArrayList<Subject> posTask = new ArrayList<Subject>();
            for(Subject s : buildings)
                if(s.task.compareTo(tasks.get(taskIndex)) == 0)
                    posTask.add(s);
            chooseType(tasks.get(taskIndex), 0, posTask);
        }
        else if(choice.compareTo("n") == 0){
            chooseTask(++taskIndex);
        }
        return true;
    }

    public boolean chooseType(String task, int typeIndex, ArrayList<Subject> posTask){
        if(typeIndex >= types.size()){
            chooseTask(tasks.indexOf(task) + 1);
            //System.out.println("Нет результатов!");
            return false;
        }
        System.out.println(types.get(typeIndex) + "?");
        System.out.print("y/n: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.compareTo("y") == 0){
            ArrayList<Subject> posType = new ArrayList<Subject>();
            for(Subject s : posTask)
                if(s.type.compareTo(types.get(typeIndex)) == 0)
                    posType.add(s);
            chooseGoal(task, types.get(typeIndex), 0, posTask, posType);
        }
        else if(choice.compareTo("n") == 0){
            chooseType(task, ++typeIndex, posTask);
        }
        return true;
    }

    public boolean chooseGoal(String task, String type, int goalIndex, ArrayList<Subject> posTask, ArrayList<Subject> posType){
        ArrayList<String> newGoals = new ArrayList<String>();
        for(Subject s : posType){
            for(String st : s.goal){
                if(!newGoals.contains(st))
                    newGoals.add(st);
            }
        }
        if(goalIndex >= newGoals.size()){
            chooseType(task, types.indexOf(type) + 1, posTask);
            return false;
        }
        System.out.println(newGoals.get(goalIndex) + "?");
        System.out.print("y/n: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.compareTo("y") == 0){
            ArrayList<Subject> posGoal = new ArrayList<Subject>();
            for(Subject s : posType){
                if(s.goal.contains(newGoals.get(goalIndex)))
                    posGoal.add(s);
            }
            confirmBuilding(task, type, newGoals.get(goalIndex), goalIndex, 0, posTask, posType, posGoal);
        }
        else if(choice.compareTo("n") == 0){
            chooseGoal(task, type, ++goalIndex, posTask, posType);
        }
        return true;
    }

    public boolean confirmBuilding(String task, String type, String goal, int goalIndex, int confirmIndex, ArrayList<Subject> posTask, ArrayList<Subject> posType, ArrayList<Subject> posGoal){
        if(confirmIndex >= posGoal.size()){
            chooseGoal(task, type, ++goalIndex, posTask, posType);
            return false;
        }
        Subject confirming = posGoal.get(confirmIndex);
        for (String c : confirming.confirmation){
            System.out.println(c + "?");
            System.out.print("y/n: ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.compareTo("n") == 0){
                confirmBuilding(task, type, goal, goalIndex, ++confirmIndex, posTask, posType, posGoal);
            }
        }
        System.out.println("Сооружение: " + confirming.name);
        System.out.println("Повторить?");
        System.out.print("y/n: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.compareTo("y") == 0){
            chooseTask(0);
        }
        if(choice.compareTo("n") == 0){
            System.exit(0);
        }
        return true;
    }
}
