import core.MessagingSystem;
import core.ProcessScheduler;
import model.Message;
import model.ScheduledTask;
import model.TextMessage;
import shared.DataTransferSystem;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        int loop = sc.nextInt();
        PriorityQueue<BigDecimal> queue = new PriorityQueue<>();

        while (sc.hasNextBigDecimal())
            queue.offer(sc.nextBigDecimal());

        while (!queue.isEmpty())
            System.out.println(queue.poll());

//        List<Integer> toShuffle = new ArrayList<>();

//        for (int i = 0; i < 50; i++)
//            toShuffle.add(i);
//        Collections.shuffle(toShuffle);
//
//        MessagingSystem system = new MessagingSystem();
//
//        for (int i = 0; i < 50; i++)
//            system.add(new TextMessage(toShuffle.get(i),  i +  " text") );
//
//        System.out.println(system.inOrderPrint());
//
//        system.getOrderedByWeight().forEach(message -> System.out.print (message.getWeight() + " " ));

//        List<Message> messages = List.of(
//                    new TextMessage(11, "test_text"),
//                    new TextMessage(6, "test_text"),
//                    new TextMessage(19, "test_text"),
//                    new TextMessage(4, "test_text"),
//                    new TextMessage(8, "test_text"),
//                    new TextMessage(17, "test_text")
//        );
//
//        MessagingSystem system =  new MessagingSystem();
//        for (Message message : messages)
//            system.add(message);


//        System.out.println(system.deleteHeaviest().getWeight());
//        System.out.println(system.getHeaviest().getWeight());
//
//        System.out.println(system.deleteLightest().getWeight());
//        System.out.println(system.getLightest().getWeight());
//
//        List<Message> orderedByWeight = system.getOrderedByWeight();
//
//        orderedByWeight.forEach(message -> System.out.print(message.getWeight() + " "));

//        ProcessScheduler processScheduler = new ProcessScheduler();
//        processScheduler.add(new ScheduledTask(1, "test_description "));
//        processScheduler.add(new ScheduledTask(2, "test_description "));

//        for (int i = 1; i <= 20; i++)
//            processScheduler.add(new ScheduledTask(i, "test_description "));

//        processScheduler.insertBefore(1, new ScheduledTask(0, "test_description "));
//        processScheduler.insertAfter(2, new ScheduledTask(3, "test_description "));

//        System.out.println(processScheduler.contains(new ScheduledTask(21, "test_description ")));
//        processScheduler.process();

//        ProcessScheduler processScheduler = new ProcessScheduler();
//        for (int i = 1; i <= 20; i++)
//            processScheduler.add(new ScheduledTask(i, "test_description "));
//
//        processScheduler.toList().forEach(x -> System.out.print(x.getId() + " "));
//        System.out.println();
//        processScheduler.reverse();
//        processScheduler.toList().forEach(x -> System.out.print(x.getId() + " "));

    }
}