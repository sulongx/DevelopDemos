package com.sulongx.functional.chapter03.common;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-16
 */
public class Artist {

    private String name;

    private String origin;

    private List<Artist> members;


    public Artist(String name,String origin){
        this(name, origin,Collections.emptyList());
    }

    public Artist(String name,String origin,List<Artist> members){
        Objects.requireNonNull(name);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(members);
        this.name = name;
        this.origin = origin;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<Artist> getMembers() {
        return members;
    }

    public void setMembers(List<Artist> members) {
        this.members = members;
    }
}
