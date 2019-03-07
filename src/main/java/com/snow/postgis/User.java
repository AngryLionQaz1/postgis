/**
 * Copyright (C), 2015-2019, 重庆了赢科技有限公司
 * FileName: User
 * Author:   萧毅
 * Date:     2019/3/7 10:58
 * Description:
 */
package com.snow.postgis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "x_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private String password;










}