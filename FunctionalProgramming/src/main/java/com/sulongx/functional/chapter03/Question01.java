package com.sulongx.functional.chapter03;

import com.sulongx.functional.chapter03.common.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-16
 */
public class Question01 {

    public static int countNums(Stream<Integer> nums){
        return nums.reduce(0,(acc,num) -> acc + num);
    }

    public static List<String> getNameAndOrigins(List<Artist> artists){
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(),artist.getOrigin()))
                .collect(toList());
    }

    public static void main(String[] args) {
        int countNums = countNums(Stream.of(Integer.valueOf(1), Integer.valueOf(2)));
        System.out.println(countNums);

        ArrayList<Artist> artists = new ArrayList<>();
        artists.add(new Artist("张三","中国"));
        artists.add(new Artist("威廉.乔治","英国"));
        artists.add(new Artist("宫本.武藏","日本"));
        artists.add(new Artist("奥巴马","美国"));
        List<String> nameAndOrigins = getNameAndOrigins(artists);
        System.out.println(nameAndOrigins);

        artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getOrigin()))
                .collect(toList());

        int i = artists.stream()
                .map(artist -> artist.getMembers().size())
                .reduce(0, Integer::sum)
                .intValue();
        System.out.println(i);

        artists.stream()
                .filter(artist -> {
                    System.out.println("惰性求值");
                    return true;
                }).count();

    }
}