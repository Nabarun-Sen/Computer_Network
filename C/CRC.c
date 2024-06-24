#include <stdio.h>
#include <string.h>

void xor(char *dividend, const char *divisor, int len) {
    for (int i = 0; i < len; i++) {
        dividend[i] = dividend[i] == divisor[i] ? '0' : '1';
    }
}

void calculate_crc(char *message, const char *generator, char *crc) {
    int message_len = strlen(message);
    int generator_len = strlen(generator);
    char temp[128];
    
    strcpy(temp, message);
    for (int i = 0; i < generator_len - 1; i++) {
        temp[message_len + i] = '0';
    }
    temp[message_len + generator_len - 1] = '\0';
    
    for (int i = 0; i <= message_len; i++) {
        if (temp[i] == '1') {
            xor(temp + i, generator, generator_len);
        }
    }

    strcpy(crc, temp + message_len);
}

int check_crc(const char *message, const char *generator) {
    int message_len = strlen(message);
    int generator_len = strlen(generator);
    char temp[128];
    
    strcpy(temp, message);
    
    for (int i = 0; i <= message_len - generator_len; i++) {
        if (temp[i] == '1') {
            xor(temp + i, generator, generator_len);
        }
    }

    for (int i = message_len - generator_len + 1; i < message_len; i++) {
        if (temp[i] == '1') {
            return 0;
        }
    }
    return 1;
}

int main() {
    char message[128], generator[128], crc[128];
    
    printf("Enter the message in binary: ");
    scanf("%s", message);
    printf("Enter the generator polynomial in binary: ");
    scanf("%s", generator);
    
    calculate_crc(message, generator, crc);
    
    printf("The calculated CRC is: %s\n", crc);
    
    strcat(message, crc);
    printf("The transmitted message with CRC is: %s\n", message);
    
    if (check_crc(message, generator)) {
        printf("No error detected.\n");
    } else {
        printf("Error detected.\n");
    }
    
    return 0;
}
