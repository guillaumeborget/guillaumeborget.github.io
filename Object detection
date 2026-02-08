import cv2
import numpy
import math

# Load the image from file
img2 = cv2.imread("couleur.png")

# Convert the image from BGR color space to HSV color space
hsv1 = cv2.cvtColor(img2, cv2.COLOR_BGR2HSV)

# Split the HSV image into its three components: Hue, Saturation, and Value
hue1, sat1, val1 = cv2.split(hsv1)

# Define lower and upper bounds for a specific hue range (green shades here)
# Hue values in OpenCV go from 0 to 179, so we divide degrees by 2
lower1 = numpy.array([36/2, 100, 0], dtype=numpy.uint8)
upper1 = numpy.array([60/2, 255, 255], dtype=numpy.uint8)

# Create a binary mask where the HSV values fall within the specified range
seg_hue1 = cv2.inRange(hsv1, lower1, upper1)

# Calculate the image moments of the binary mask
M = cv2.moments(seg_hue1)

# Calculate the coordinates of the centroid (barycenter) using the moments
cX = int(M["m10"] / M["m00"])
cY = int(M["m01"] / M["m00"])

# Draw a small circle at the centroid location
cv2.circle(seg_hue1, (cX, cY), 3, (155, 155, 155), -1)

# Add a label near the centroid
cv2.putText(seg_hue1, "barycentre", (cX - 25, cY - 25), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2) 

# Display the original image and the binary segmented mask
cv2.imshow("couleur", img2)
cv2.imshow("seg_hue", seg_hue1)

# Wait indefinitely until a key is pressed
cv2.waitKey(0)

# Print the coordinates of the centroid
print("x = ", cX, ", y = ", cY)
