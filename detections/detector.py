from ultralytics import YOLO
import cv2
import json
from collections import defaultdict
import time

# Configuración inicial
model = YOLO("yolov8n.pt")
cap = cv2.VideoCapture("data/video1.mp4")

if not cap.isOpened():
    print("Error al abrir el video")
    exit()

# Parámetros del video
fps = cap.get(cv2.CAP_PROP_FPS)
frame_delay = int(1000 / fps)  # Tiempo entre frames en ms
original_size = (int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)), int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)))
display_size = (700, int(700 * original_size[1] / original_size[0]))

cv2.namedWindow("Detección en Tiempo Real", cv2.WINDOW_NORMAL)
cv2.resizeWindow("Detección en Tiempo Real", display_size[0], display_size[1])

# Variables de control
resumen_data = {"detections": []}
last_annotated = None
detection_interval = 1000  # Milisegundos entre detecciones
last_detection_time = 0
start_time = time.time() * 1000  # Tiempo en milisegundos

while True:
    ret, frame = cap.read()
    if not ret:
        break

    current_time = time.time() * 1000 - start_time
    
    # Procesar detecciones según el intervalo
    if current_time - last_detection_time >= detection_interval:
        results = model(frame)
        last_annotated = results[0].plot()
        last_detection_time = current_time

        # Registrar detecciones
        counts = defaultdict(int)
        for box in results[0].boxes:
            if float(box.conf) >= 0.3:
                counts[model.names[int(box.cls)]] += 1

        resumen_data["detections"].append({
            "timestamp_ms": int(current_time),
            "objects": dict(counts)
        })

    # Mostrar frame (anotado o original)
    display_frame = last_annotated if last_annotated is not None else frame
    resized_frame = cv2.resize(display_frame, display_size)
    cv2.imshow("Detección en Tiempo Real", resized_frame)

    # Control de velocidad
    key = cv2.waitKey(frame_delay) & 0xFF
    if key in (ord('q'), 27):  # Tecla Q o ESC
        break

cap.release()
cv2.destroyAllWindows()

# Guardar resultados con precisión de milisegundos
with open("detections_precision.json", "w") as f:
    json.dump(resumen_data, f, indent=4)