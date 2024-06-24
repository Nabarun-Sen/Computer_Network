#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef struct {
    int seq_num;
    char data[100];
} Frame;

void send_frame(Frame frame) {
    printf("Sending frame %d: %s\n", frame.seq_num, frame.data);
    sleep(1);  // Simulate transmission delay
}

int receive_ack(int seq_num) {
    char response[10];
    printf("Did you receive ACK for frame %d? (yes/no): ", seq_num);
    scanf("%s", response);
    if (strcmp(response, "yes") == 0) {
        printf("ACK for frame %d received.\n", seq_num);
        return 1;
    } else {
        printf("ACK for frame %d not received.\n", seq_num);
        return 0;
    }
}

void sliding_window(Frame *frames, int frame_count, int window_size) {
    int base = 0;
    int next_seq_num = 0;

    while (base < frame_count) {
        // Send frames within the window
        while (next_seq_num < base + window_size && next_seq_num < frame_count) {
            send_frame(frames[next_seq_num]);
            next_seq_num++;
        }

        // Check for acknowledgments
        for (int i = base; i < next_seq_num; i++) {
            if (!receive_ack(i)) {
                printf("Resending frame %d\n", i);
                send_frame(frames[i]);
            } else {
                base++; // Move the base of the window
            }
        }
    }
}

int main() {
    int frame_count, window_size;

    printf("Enter the number of frames: ");
    scanf("%d", &frame_count);

    Frame *frames = (Frame *)malloc(frame_count * sizeof(Frame));

    for (int i = 0; i < frame_count; i++) {
        printf("Enter data for frame %d: ", i);
        scanf("%s", frames[i].data);
        frames[i].seq_num = i;
    }

    printf("Enter the window size: ");
    scanf("%d", &window_size);

    sliding_window(frames, frame_count, window_size);

    free(frames);
    return 0;
}