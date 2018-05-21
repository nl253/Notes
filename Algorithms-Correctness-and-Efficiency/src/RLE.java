import java.util.ArrayList;
import java.util.Arrays;

class RLE {

    // Given a RLE encoded array, get the size of the decoded array.
    public static int decodedSize(int[] data) {
        int sum = 0;

        for (int i = 0; i < data.length; i += 2)
            sum += data[i];

        return sum;
    }

    // Decode a RLE array
    public static int[] decode(int[] data) {
        int[] decoded = new int[decodedSize(data)];
        int outIdx = 0;

        for (int i = 0; i < data.length; i += 2) {
            // Write the content the specified number of times.
            for (int j = 0; j < data[i]; j++) {
                decoded[outIdx] = data[i + 1];
                outIdx++;
            }
        }

        return decoded;
    }

    // RLE encode an array
    public static int[] encode(int[] data) {
        ArrayList<Integer> encoded = new ArrayList<Integer>();
        int i = 0;

        while (i < data.length) {
            int current = data[i];
            int runLength = 0;

            // count how long the next run is.
            while (i < data.length && data[i] == current) {
                i++;
                runLength++;
            }

            encoded.add(runLength);
            encoded.add(current);
        }

        // Move the ArrayList into an array
        int[] encoded2 = new int[encoded.size()];

        for (i = 0; i < encoded.size(); i++)
            encoded2[i] = encoded.get(i);

        return encoded2;
    }

    public static void main(String[] args) {
        int[] encoded = {2, 0, 1, 1, 1, 0, 5, 2, 3, 0, 4, 1};
        int[] decoded = decode(encoded);
        System.out.println("decoded: ");

        for (int i = 0; i < decoded.length; i++)
            System.out.print(decoded[i]);

        System.out.println("");
        int[] reEncoded = encode(decoded);
        System.out.println("reEncoded: ");

        for (int i = 0; i < reEncoded.length; i++)
            System.out.print(reEncoded[i]);

        System.out.println("");
        // NOT encoded.equals(reEncoded), since that doesn't look at the array's
        // contents.
        System.out.println(Arrays.equals(encoded, reEncoded));
    }
}
