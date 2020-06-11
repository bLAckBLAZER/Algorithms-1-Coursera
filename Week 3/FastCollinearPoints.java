/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();
    private LineSegment[] lineSegments;

    // Arraylist of Point arrays which will hold start and end points of all lineSegments found
    private ArrayList<Point[]> allLineSegments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (nullPresent(points)) {
            throw new IllegalArgumentException();
        }


        // Creating a copy of points array
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        // Sorting the copy array
        Arrays.sort(pointsCopy);
		
		if (duplicatesPresent(pointsCopy)) {
            throw new IllegalArgumentException();
        }

        // // test
        // System.out.println("Sorted pointsCopy: ");
        // for (Point p : pointsCopy) {
        //     System.out.println(p);
        // }
        // System.out.println();


        double currentSlope = 0;
        double previousSlope = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < pointsCopy.length; i++) {
            Point origin = pointsCopy[i];
            Arrays.sort(points, origin.slopeOrder());
            ArrayList<Point> segmentPoints = new ArrayList<>();


            for (int j = 0; j < points.length; j++) {
                currentSlope = origin.slopeTo(points[j]);


                if (currentSlope == previousSlope) {
                    segmentPoints.add(points[j]);
                }
                else {
                    segmentPoints.add(origin);
                    addSegment(segmentPoints);
                    segmentPoints.clear();
                    segmentPoints.add(points[j]);

                }
                previousSlope = currentSlope;


            }
            segmentPoints.add(origin);
            addSegment(segmentPoints);

        }

        lineSegments = segments.toArray(new LineSegment[segments.size()]);


    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;

    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, lineSegments.length);

    }


    private void addSegment(ArrayList<Point> segmentPoints) {
        if (segmentPoints.size() < 4) {
            return;


        }
        Collections.sort(segmentPoints);


        Point origin = segmentPoints.get(0);
        Point end = segmentPoints.get(segmentPoints.size() - 1);

        Point[] temp = new Point[2];
        temp[0] = origin;
        temp[1] = end;

        if (allLineSegments.isEmpty()) {
            allLineSegments.add(temp);
        }
        else {
            // check if already present
            for (int i = 0; i < allLineSegments.size(); i++) {
                if (allLineSegments.get(i)[0].compareTo(temp[0]) == 0
                        && allLineSegments.get(i)[1].compareTo(temp[1]) == 0) {
                    // System.out.println("Skipped points " + temp[0] + " " + temp[1]);
                    return;
                }

            }
            allLineSegments.add(temp);
        }
        segments.add(new LineSegment(origin, end));


        // System.out.println("Added");

    }

    // Check if there no null values
    private boolean nullPresent(Point[] points) {
        if (points == null) {
            return true;
        }

        for (Point point : points) {
            if (point == null) {
                return true;
            }
        }

        return false;
    }

    // Check if there no duplicated points
    private boolean duplicatesPresent(Point[] points) {
        for (int i = 0; i < (points.length - 1); i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // System.out.println(collinear.segments().length);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
