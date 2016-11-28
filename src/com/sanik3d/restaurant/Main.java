package com.sanik3d.restaurant;

/**
 * Created by Александр on 06.11.2016.
 */
public class Main {
//    public static void main(String[] args) {
//
//        Category[] categories = new Category[]{
//                new Category("Горячее блюдо"),// 0
//                new Category("Суп"),          // 1
//                new Category("Морепродукт"),  // 2
//                new Category("Птица"),        // 3
//                new Category("Мясо"),         // 4
//                new Category("Салат"),        // 5
//                new Category("Закуска")       // 6
//        };
////        Dish[] dishes = new Dish[]{
////                new Dish("Уха", new Category[]{categories[1], categories[2]}, 400),
////                new Dish("Рыбное заливное", new Category[]{categories[2], categories[6]}, 280),
////                new Dish("Канапе мясное", new Category[]{categories[4], categories[6]}, 50),
////                new Dish("Селедка под шубой", new Category[]{categories[2], categories[2]}, 150),
////                new Dish("Стейк из говядины", new Category[]{categories[0], categories[4]}, 600),
////                new Dish("Тарталетка с паштетом из куриной грудки", new Category[]{categories[3], categories[6]}, 50)
////        };
////        Menu firstMenu = new Menu(dishes, categories);
//
//        Saver.write(firstMenu);
//        Menu loaded = Loader.read();
//        menuToScreen(loaded);
//
//    }
//
//    public static void menuToScreen(Menu menu){
//        Category[] categories = menu.getCategories();
//        Dish[] dishes = menu.getDishes();
//
//        System.out.println("-----Категории блюд-----\n");
//        System.out.println("----------Название--------");
//        for (Category category : categories) {
//            System.out.println(category);
//        }
//        System.out.println();
//        System.out.println("---------Блюда---------\n");
//        System.out.println("----------------Название----------------- ---Стоимость--- ---Категории---");
//        for (Dish dish : dishes) {
//            System.out.printf("%40s %15d %s\n", dish.getName(), dish.getCost(), Arrays.toString(dish.getCategory()));
//        }
//    }
}
