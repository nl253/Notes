#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""

Description:
    Project Description

Contact:
    Author: nl253
    Github: https://github.com/nl253
    Email:  nl253@kent.ac.uk

Todo:
    * For module TODOs
    * You have to also use sphinx.ext.todo extension
"""

# Standard Library
import logging
import os
import sys
from logging import Logger
# import types for static typing (mypy, pycharm etc)
from typing import Iterable, Iterator, List

# initalise logging with sane configuration
logging.basicConfig(
    level=logging.DEBUG,
    format="%(levelname)s:%(asctime)s  %(message)s"
)

log: Logger = logging.getLogger()

# run only if run as script
if __name__ == "__main__":
    pass
