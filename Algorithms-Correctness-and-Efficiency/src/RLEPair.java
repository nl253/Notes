import java.util.ArrayList;
import java.util.Arrays;

class EncPair {

    int runLength;
    // NB, using character data instead of integer
    char content;

    public EncPair(int rl, char c) {
        runLength = rl;
        content = c;
    }

    public String toString() {
        return ("<" + runLength + "," + content + ">");
    }

    public boolean equals(Object p) {
        return runLength == ((EncPair) p).runLength && content == ((EncPair) p).content;
    }
}

class RLEPair {

    // Given a RLE encoded array, get the size of the decoded array.
    public static int decodedSize(EncPair[] data) {
        int sum = 0;

        // Increment by 1 now
        for (int i = 0; i < data.length; i++)
            sum += data[i].runLength;

        return sum;
    }

    // Decode a RLE array
    public static char[] decode(EncPair[] data) {
        char[] decoded = new char[decodedSize(data)];
        int outIdx = 0;

        for (int i = 0; i < data.length; i++) {
            // Write the content the specified number of times.
            for (int j = 0; j < data[i].runLength; j++) {
                decoded[outIdx] = data[i].content;
                outIdx++;
            }
        }

        return decoded;
    }

    // RLE encode an array
    public static EncPair[] encode(char[] data) {
        ArrayList<EncPair> encoded = new ArrayList<EncPair>();
        int i = 0;

        while (i < data.length) {
            char current = data[i];
            int runLength = 0;

            // count how long the next run is.
            while (i < data.length && data[i] == current) {
                i++;
                runLength++;
            }

            encoded.add(new EncPair(runLength, current));
        }

        // Move the ArrayList into an array
        EncPair[] encoded2 = new EncPair[encoded.size()];

        for (i = 0; i < encoded.size(); i++)
            encoded2[i] = encoded.get(i);

        return encoded2;
    }

    public static void main(String[] args) {
        EncPair[] encoded = {new EncPair(2, 'a'), new EncPair(1, 'b'), new EncPair(1, 'a'), new EncPair(5, 'c'), new EncPair(3, 'a'), new EncPair(4, 'b')};
        char[] decoded = decode(encoded);
        System.out.println("decoded: ");

        for (int i = 0; i < decoded.length; i++)
            System.out.print(decoded[i]);

        System.out.println("");
        EncPair[] reEncoded = encode(decoded);
        System.out.println("reEncoded: ");

        for (int i = 0; i < reEncoded.length; i++)
            System.out.print(reEncoded[i]);

        System.out.println("");
        // Use deepEquals, to call .equals on the EncPairs
        System.out.println(Arrays.deepEquals(encoded, reEncoded));
    }
}
