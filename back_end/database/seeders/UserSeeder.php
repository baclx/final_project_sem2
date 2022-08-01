<?php

namespace Database\Seeders;

use App\Models\User;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $arr = [];
        $faker = \Faker\Factory::create();

        for ($i = 1; $i <= 10; $i++){
            $arr[] = [
                'name' => $faker->firstName . ' ' . $faker->lastName,
                'email' => $faker->email,
                'password' => $faker->password,
                'phone' => '0' . $faker->phoneNumber(9),
                'gender' => $faker->boolean,
            ];
            User::insert($arr);
        }
    }
}
