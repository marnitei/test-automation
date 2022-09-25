package models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AccountModel {
    String accountName;
    String phone;
    String fax;
    String website;
    String parentAccount;
}
