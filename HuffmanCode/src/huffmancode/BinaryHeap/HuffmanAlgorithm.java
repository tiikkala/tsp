package huffmancode.BinaryHeap;

/**
 * This algorithm builds the Huffman tree from a given min heap.
 */
public class HuffmanAlgorithm {

    /**
     * Method creates a min heap representation of the input data.
     *
     * @param characters Array of characters to be coded.
     * @param freq Frequencies of the characters soterd in array.
     * @param size The number of different characters.
     * @return Characters stored in a min heap according to their frequency.
     */
    private BinaryHeap createAndBuildHeap(char[] characters, int[] freq, int size) {
        BinaryHeap heap = new BinaryHeap(size);
        for (int i = 0; i < size; i++) {
            heap.insert(new Node(characters[i], freq[i]));
        }
        return heap;
    }

    /**
     * Method builds the Huffman tree of the input data. First the algorithm
     * arranges the characters accoriding to their frequency into a min heap.
     * Then algorithm iterate through the heap and extracts to characters with
     * minimum frequency, creates a new node by summing their frequencies and
     * setting them as children of th new node, and adding this new node to the
     * heap. Algorithm is finished, when the heap contains only one node, that
     * is the root node of the Huffman tree.
     *
     * @param characters Array of characters to be coded.
     * @param freq Frequencies of the characters soterd in array.
     * @param size The number of different characters.
     * @return Huffman tree representing the characters.
     */
    public Node buildHuffmanTree(char[] characters, int[] freq, int size) {
        Node left;
        Node right;
        Node root;
        BinaryHeap heap = createAndBuildHeap(characters, freq, size);
        while (heap.getSize() > 1) {
            left = (Node) heap.poll();
            right = (Node) heap.poll();
            // the ¤ character is never used, the actual characters are
            // int the leaves
            root = new Node('¤', left.getFreq() + right.getFreq());
            root.setLeftChild(left);
            root.setRigthChild(right);
            heap.insert(root);
        }
        return (Node) heap.poll();
    }

    public void buildCode(Node node, String[] codeTable, StringBuilder code) {
        if (!node.isLeaf()) {
            buildCode(node.getLeftChild(), codeTable, code.append("0"));
            code.deleteCharAt(code.length()-1);
            buildCode(node.getRightChild(), codeTable, code.append("1"));
            code.deleteCharAt(code.length()-1);
        } else {
            codeTable[node.getChar()] = code.toString();
        }
    }

    public void printCodes(String[] codeTable) {
        for (int i = 0; i < codeTable.length; i++) {
            if (codeTable[i] != null) {
                System.out.println((char) i + ": " + codeTable[i]);
            }
        }
    }
}
