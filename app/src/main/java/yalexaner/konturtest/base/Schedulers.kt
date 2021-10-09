package yalexaner.konturtest.base

import rx.Scheduler

interface Schedulers {
    val io: Scheduler
    val main: Scheduler
}