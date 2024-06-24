import time

class Frame:
    def init(self, seq_num, data):
        self.seq_num = seq_num
        self.data = data

def send_frame(frame):
    print(f"Sending frame {frame.seq_num}: {frame.data}")
    time.sleep(1)  # Simulate transmission delay
    return frame

def receive_ack(seq_num):
    ack = input(f"Did you receive ACK for frame {seq_num}? (yes/no): ").lower()
    if ack == 'yes':
        print(f"ACK for frame {seq_num} received.")
        return True
    else:
        print(f"ACK for frame {seq_num} not received.")
        return False

def go_back_n(frames, window_size):
    n = len(frames)
    base = 0
    next_seq_num = 0
    while base < n:
        while next_seq_num < base + window_size and next_seq_num < n:
            frame = Frame(next_seq_num, frames[next_seq_num])
            send_frame(frame)
            next_seq_num += 1

        ack_received = True
        for i in range(base, next_seq_num):
            if not receive_ack(i):
                ack_received = False
                break

        if ack_received:
            base = next_seq_num
        else:
            print(f"Resending window starting from frame {base}")
            next_seq_num = base

def main():
    frames = []
    while True:
        frame_data = input("Enter frame data (or 'done' to finish): ")
        if frame_data.lower() == 'done':
            break
        frames.append(frame_data)

    window_size = int(input("Enter the window size: "))
    go_back_n(frames, window_size)

# Directly call the main function
main()