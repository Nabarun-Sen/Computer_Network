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

void stop_and_wait(Frame *frames, int frame_count) {
    int seq_num = 0;

    while (seq_num < frame_count) {
        Frame frame = frames[seq_num];
        int ack_received = 0;

        while (!ack_received) {
            send_frame(frame);
            ack_received = receive_ack(seq_num);

            if (!ack_received) {
                printf("Resending frame %d\n", seq_num);
                sleep(1);  // Simulate waiting before resending
            }
        }

        seq_num++;
    }
}

int main() {
    int frame_count;

    printf("Enter the number of frames: ");
    scanf("%d", &frame_count);

    Frame *frames = (Frame *)malloc(frame_count * sizeof(Frame));

    for (int i = 0; i < frame_count; i++) {
        printf("Enter data for frame %d: ", i);
        scanf("%s", frames[i].data);
        frames[i].seq_num = i;
    }

    stop_and_wait(frames, frame_count);

    free(frames);
    return 0;
}