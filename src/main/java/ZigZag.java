/**A sequence of numbers is called a zig-zag sequence if the
 *differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists)
 * may be either positive or negative. A sequence with fewer than two
 * elements is trivially a zig-zag sequence.
 *
 *      For example, 1,7,4,9,2,5 is a zig-zag sequence because the differences
 *      (6,-3,5,-7,3) are alternately positive and negative.
 *      In contrast, 1,4,7,2,5 and 1,7,4,5,5 are not zig-zag sequences,
 *      the first because its first two differences are positive and the second
 *      because its last difference is zero.
 *
 *      Given a sequence of integers, sequence, return the length of the longest
 *      subsequence of sequence that is a zig-zag sequence. A subsequence is obtained
 *      by deleting some number of elements (possibly zero) from the original sequence,
 *      leaving the remaining elements in their original order.
 **/
public class ZigZag {

    private static int[] testResults = {6,7,1,2,8,36};
    private static int testIndex = 0;

    public static class Sequence{
       int order;
       int lastElement;
       int lasDif;
       int[] sequence;

       public Sequence(int[] sequence, int order, int lastElement, int lasDif){
           this.sequence = sequence;
           this.order = order;
           this.lastElement = lastElement;
           this.lasDif = lasDif;
       }
    }


    public static int longestZigZag(int[] sequence){

      Sequence[][] sequences = new Sequence[sequence.length+1][sequence.length];

      for(int i=0;i<sequence.length;i++){
        int[] sequenceNumber = new int[1];
        sequenceNumber[0] = sequence[i];
        Sequence generatedSequence = new Sequence(sequenceNumber,i,sequence[i],2);
        sequences[1][i] = generatedSequence;
      }
      int longest = 1;


      for(int i=2;i<sequences.length;i++){
          boolean sequenceFound = false;
        for(int j=i-1;j<sequence.length;j++){
           for(int k=0;k<j;k++){
               if(sequences[i-1][k]!=null){
                   Sequence generatedSequence = verify(sequences[i-1][k],sequence[j],j);
                   if(generatedSequence!=null){
                       sequences[i][j] = generatedSequence;
                       sequenceFound=true;
                   }
               }
           }
        }
        if(sequenceFound)
            longest=i;
      }

      return longest;
    }

    private static Sequence verify(Sequence sequence, int value, int order){
       if(sequence!=null){
           if(order>sequence.order){
               int dif = sequence.lastElement - value;
               dif=dif!=0?dif/Math.abs(dif):dif;
               if(dif*sequence.lasDif==-1 || sequence.lasDif==2) {
                   int[] sequenceNumbers = new int[sequence.sequence.length+1];
                   for(int i=0;i<sequence.sequence.length;i++){
                       sequenceNumbers[i] = sequence.sequence[i];
                   }
                   sequenceNumbers[sequenceNumbers.length-1] = value;
                   return new Sequence(sequenceNumbers,order,value,dif);
               }
           }
       }
       return null;
    }

}
