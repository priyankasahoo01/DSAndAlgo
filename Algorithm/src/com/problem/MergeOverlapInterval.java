package com.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeOverlapInterval {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        List<Interval> li = new ArrayList<>();
        Collections.sort(li, new Comparator<Interval>() {

            @Override
            public int compare(Interval o1, Interval o2) {
                
                int diffStart = o1.start-o2.start;
                if(diffStart==0) {
                    diffStart = o1.end-o2.end;
                }
                return diffStart;
            }
        });

    }

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

}
