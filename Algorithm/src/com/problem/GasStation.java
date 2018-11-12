package com.problem;
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.

You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2..
Completing the circuit means starting at i and ending up at i again.
 * @author priysaho
 *
 */
public class GasStation {
    
    public static void main(String[] args) {
        int a[]= { 204, 918, 18, 17, 35, 739, 913, 14, 76, 555, 333, 535, 653, 667, 52, 987, 422, 553, 599, 765, 494, 298, 16, 285, 272, 485, 989, 627, 422, 399, 258, 959, 475, 983, 535, 699, 663, 152, 606, 406, 173, 671, 559, 594, 531, 824, 898, 884, 491, 193, 315, 652, 799, 979, 890, 916, 331, 77, 650, 996, 367, 86, 767, 542, 858, 796, 264, 64, 513, 955, 669, 694, 382, 711, 710, 962, 854, 784, 299, 606, 655, 517, 376, 764, 998, 244, 896, 725, 218, 663, 965, 660, 803, 881, 482, 505, 336, 279 };
        int b[] = { 273, 790, 131, 367, 914, 140, 727, 41, 628, 594, 725, 289, 205, 496, 290, 743, 363, 412, 644, 232, 173, 8, 787, 673, 798, 938, 510, 832, 495, 866, 628, 184, 654, 296, 734, 587, 142, 350, 870, 583, 825, 511, 184, 770, 173, 486, 41, 681, 82, 532, 570, 71, 934, 56, 524, 432, 307, 796, 622, 640, 705, 498, 109, 519, 616, 875, 895, 244, 688, 283, 49, 946, 313, 717, 819, 427, 845, 514, 809, 422, 233, 753, 176, 35, 76, 968, 836, 876, 551, 398, 12, 151, 910, 606, 932, 580, 795, 187 };
        
        GasStation g = new GasStation();
        int op = g.canCompleteCircuit(a, b);
        System.out.println("op --> "+op);
        
        
       int[] i1 = new int[]{ 959, 329, 987, 951, 942, 410, 282, 376, 581, 507, 546, 299, 564, 114, 474, 163, 953, 481, 337, 395, 679, 21, 335, 846, 878, 961, 663, 413, 610, 937, 32, 831, 239, 899, 659, 718, 738, 7, 209 };
    int[] i2 = new int[] { 862, 783, 134, 441, 177, 416, 329, 43, 997, 920, 289, 117, 573, 672, 574, 797, 512, 887, 571, 657, 420, 686, 411, 817, 185, 326, 891, 122, 496, 905, 910, 810, 226, 462, 759, 637, 517, 237, 884 };
    op =  g.canCompleteCircuit(i1, 
                i2);
       System.out.println("op --> "+op);
       
       
       op = g.mymethod(i1, i2);
       System.out.println("op --> "+op);
       
       int[] j1= { 383, 521, 491, 907, 871, 705 };
       int[] j2= {96, 197, 592, 67, 77, 641};
       System.out.println("op -->"+g.canCompleteCircuit(j1, j2));
       System.out.println("op -->"+g.mymethod(j1, j2));
    }
    
    public int sum(int[] a){
        int sum = 0;
        for(int i : a){sum = sum +i;}
        return sum;
    }
    public int canCompleteCircuit(final int[] a, final int[] b) {
        
//        return mymethod(a, b);
        
        int min_S=Integer.MAX_VALUE, S=0, position=0;
        int n = a.length;
        for(int i=0;i<n;i++)
        {
            int diff = a[i] - b[i];
            S += diff;
            if(S<min_S)
            {
                min_S = S;
                position = (i+1) % n;
            }
        }
        if(S>=0)
            return position;
            return -1;
        
        
    }

    private int mymethod(final int[] a, final int[] b) {
        int S = 0;
        int n = a.length;
        int min_S =Integer.MAX_VALUE;int pos=-1;
        for(int i =0;i<n;i++){
            int diff=a[i]-b[i];
            S=S+diff;
            if(S<min_S){
                min_S = S;
                pos = (i+1)%n;
                
            }
            
        }
        if(S<0){return -1;}
        return pos;
    }

}
